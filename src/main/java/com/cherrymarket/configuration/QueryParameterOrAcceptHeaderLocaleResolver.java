package com.cherrymarket.configuration;

import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.List;
import java.util.Locale;

@Component
public class QueryParameterOrAcceptHeaderLocaleResolver extends AcceptHeaderLocaleResolver {

    private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

    @PostConstruct
    public void init() {
        setDefaultLocale(DEFAULT_LOCALE);
        setSupportedLocales(supportedLocales());
    }

    @Override
    @Nonnull
    public Locale resolveLocale(HttpServletRequest request) {
        String localeParameter = request.getParameter("lang");
        String localeHeader = request.getHeader("Accept-Language");

        if (localeParameter != null && !localeParameter.isBlank()) {
            Locale locale = asSupportedLocale(Locale.of(localeParameter));

            if (locale != null)
                return locale;
        }

        if (localeHeader != null && !localeHeader.isBlank()) {
            Locale locale = Locale.lookup(Locale.LanguageRange.parse(localeHeader), supportedLocales());

            if (locale != null)
                return locale;
        }

        return DEFAULT_LOCALE;
    }

    private Locale asSupportedLocale(Locale locale) {
        return supportedLocales().stream()
                .filter(l -> l.getLanguage().equalsIgnoreCase(locale.getLanguage()))
                .findFirst()
                .orElse(null);
    }

    private List<Locale> supportedLocales() {
        return List.of(
                Locale.of("en"),
                Locale.of("uk")
        );
    }

}
