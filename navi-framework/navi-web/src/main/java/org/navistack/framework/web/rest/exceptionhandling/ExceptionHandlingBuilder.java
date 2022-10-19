package org.navistack.framework.web.rest.exceptionhandling;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Collection;
import java.util.LinkedList;

@Accessors(chain = true)
public class ExceptionHandlingBuilder {
    @Getter
    @Setter
    private boolean includeStackTrace = false;

    @Getter
    @Setter
    private MessageSource messageSource;

    @Getter
    @Setter
    private LocaleResolver localeResolver;

    @Getter
    @Setter
    private ExceptionTranslator fallbackTranslator;

    @Getter
    private final Collection<ExceptionTranslator> translators = new LinkedList<>();

    @Getter
    private final Collection<ExceptionHandlingConfigurer> configurers = new LinkedList<>();

    public ExceptionHandlingBuilder() {
    }

    public ExceptionHandlingBuilder(Collection<? extends ExceptionTranslator> translators) {
        this.addTranslators(translators);
    }

    public ExceptionHandlingBuilder addTranslator(ExceptionTranslator translator) {
        if (translator == null) {
            throw new NullPointerException("throwableType must not be null");
        }
        this.translators.add(translator);
        return this;
    }

    public ExceptionHandlingBuilder addTranslators(Collection<? extends ExceptionTranslator> translators) {
        if (translators == null || translators.isEmpty()) {
            return this;
        }
        this.translators.addAll(translators);
        return this;
    }

    public ExceptionHandlingBuilder applyConfigurer(ExceptionHandlingConfigurer configurer) {
        if (configurer == null) {
            throw new NullPointerException("configurer must not be null");
        }
        this.configurers.add(configurer);
        return this;
    }

    public ExceptionHandlingBuilder applyConfigurers(Collection<? extends ExceptionHandlingConfigurer> configurers) {
        if (configurers == null || configurers.isEmpty()) {
            return this;
        }
        this.configurers.addAll(configurers);
        return this;
    }

    public ExceptionHandling build() {
        ExceptionHandling exceptionHandling = new ExceptionHandling();
        exceptionHandling.setIncludeStackTrace(includeStackTrace);
        if (messageSource != null) {
            exceptionHandling.setMessageSource(messageSource);
        } else {
            exceptionHandling.setMessageSource(new ReloadableResourceBundleMessageSource());
        }
        if (localeResolver != null) {
            exceptionHandling.setLocaleResolver(localeResolver);
        } else {
            exceptionHandling.setLocaleResolver(new AcceptHeaderLocaleResolver());
        }
        if (fallbackTranslator != null) {
            exceptionHandling.setFallbackTranslator(fallbackTranslator);
        }
        if (!translators.isEmpty()) {
            exceptionHandling.addTranslators(translators);
        }
        if (!configurers.isEmpty()) {
            for (ExceptionHandlingConfigurer configurer : configurers) {
                configurer.configure(exceptionHandling);
            }
        }
        return exceptionHandling;
    }
}
