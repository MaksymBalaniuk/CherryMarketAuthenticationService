package com.cherrymarket.configuration.localization;

import jakarta.annotation.Nonnull;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.lang.Nullable;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.*;

public class YamlMessageSource extends AbstractMessageSource {

    private final String basename;
    private final Locale defaultLocale;
    private final Map<Locale, Map<String, String>> messages = new HashMap<>();

    public YamlMessageSource(String basename, Locale defaultLocale, Set<Locale> supportedLocales) {
        this.basename = basename;
        this.defaultLocale = defaultLocale;
        supportedLocales.add(defaultLocale);
        supportedLocales.forEach(this::loadMessages);
    }

    @Override
    @Nullable
    protected MessageFormat resolveCode(@Nonnull String code, @Nonnull Locale locale) {
        String message = getMessageForLocale(code, locale);

        if (message == null)
            message = getMessageForLocale(code, defaultLocale);

        return message != null ? new MessageFormat(message, locale) : null;
    }

    private String getMessageForLocale(String code, Locale locale) {
        Map<String, String> localized = messages.get(locale);
        return localized == null ? null : localized.get(code);
    }

    private void loadMessages(Locale locale) {
        String filename = basename + "_" + locale.getLanguage() + ".yaml";
        ClassPathResource resource = new ClassPathResource(filename);

        if (!resource.exists())
            return;

        try (InputStream input = resource.getInputStream()) {
            Map<String, Object> data = new Yaml().load(input);
            Map<String, String> flat = flatten(data, null);
            messages.put(locale, flat);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to load localization file '%s'", filename));
        }
    }

    private Map<String, String> flatten(Map<String, Object> source, String prefix) {
        Map<String, String> result = new HashMap<>();

        for (Map.Entry<String, Object> entry : source.entrySet()) {
            String key = prefix != null ? prefix + "." + entry.getKey() : entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Map<?, ?> nestedValue) {
                Map<String, Object> castedNestedValue = new HashMap<>();

                for (Map.Entry<?, ?> nestedValueEntry : nestedValue.entrySet())
                    if (nestedValueEntry.getKey() instanceof String && nestedValueEntry.getValue() != null)
                        castedNestedValue.put(nestedValueEntry.getKey().toString(), nestedValueEntry.getValue());

                result.putAll(flatten(castedNestedValue, key));
            } else {
                result.put(key, String.valueOf(value));
            }
        }

        return result;
    }

}
