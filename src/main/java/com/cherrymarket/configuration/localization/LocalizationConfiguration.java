package com.cherrymarket.configuration.localization;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;

@Configuration
public class LocalizationConfiguration {

    public static final Locale DEFAULT_LOCALE = Locale.of("en");

    @Value("${spring.messages.basename}")
    String basename;

    @Bean
    public MessageSource messageSource() {
        return new YamlMessageSource(basename, DEFAULT_LOCALE, new HashSet<>(supportedLocales()));
    }

    @Bean
    public LocaleResolver localeResolver() {
        QueryParameterOrAcceptHeaderLocaleResolver localeResolver = new QueryParameterOrAcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(DEFAULT_LOCALE);
        localeResolver.setSupportedLocales(supportedLocales());
        return localeResolver;
    }

    public static List<Locale> supportedLocales() {
        return List.of(
                Locale.of("en"),
                Locale.of("uk")
        );
    }

}
