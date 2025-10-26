package com.cherrymarket.configuration.localization;

import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

public class QueryParameterOrAcceptHeaderLocaleResolver extends AcceptHeaderLocaleResolver {

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
            Locale locale = Locale.lookup(Locale.LanguageRange.parse(localeHeader), getSupportedLocales());

            if (locale != null)
                return locale;
        }

        return Locale.getDefault();
    }

    private Locale asSupportedLocale(Locale locale) {
        return getSupportedLocales().stream()
                .filter(l -> l.getLanguage().equalsIgnoreCase(locale.getLanguage()))
                .findFirst()
                .orElse(null);
    }

}
