package org.navistack.framework.web.rest.exceptionhandling;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.navistack.framework.web.rest.ExceptionalEntity;
import org.navistack.framework.web.rest.ExceptionalEntityBuilder;
import org.navistack.framework.web.rest.RestErrResult;
import org.navistack.framework.web.rest.exceptionhandling.translators.general.ThrowableTranslator;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Slf4j
@RestControllerAdvice
public class ExceptionHandling {
    private final Collection<ExceptionTranslator> translators = new LinkedList<>();
    private final Map<Class<?>, ExceptionTranslator> mappedTranslators = new ConcurrentHashMap<>();
    @Setter
    private boolean includeStackTrace = false;
    @Setter
    @NonNull
    private MessageSource messageSource;
    @Setter
    @NonNull
    private LocaleResolver localeResolver;
    @Setter
    @NonNull
    private ExceptionTranslator fallbackTranslator = new ThrowableTranslator();

    public void addTranslator(ExceptionTranslator translator) {
        if (translator == null) {
            throw new NullPointerException("throwableType must not be null");
        }
        this.translators.add(translator);
    }

    public void addTranslators(Collection<? extends ExceptionTranslator> translators) {
        if (translators == null || translators.isEmpty()) {
            return;
        }
        this.translators.addAll(translators);
    }

    @ExceptionHandler
    public ResponseEntity<RestErrResult> handleThrowable(
            Throwable throwable, HttpServletRequest request) {
        RestErrResult err = translateException(throwable, request);
        logException(err);
        return buildResponseEntity(err, request);
    }

    protected void logException(RestErrResult err) {
        Throwable throwable = err.getThrowable();
        HttpStatus httpStatus = err.getStatus();
        if (httpStatus.is4xxClientError()) {
            log.warn("{}: {}", httpStatus.getReasonPhrase(), throwable.getMessage());
        } else if (httpStatus.is5xxServerError()) {
            log.error(httpStatus.getReasonPhrase(), throwable);
        }
    }

    protected ResponseEntity<RestErrResult> buildResponseEntity(RestErrResult err, HttpServletRequest request) {
        Throwable throwable = err.getThrowable();
        HttpStatus httpStatus = err.getStatus();
        String message = err.getMessage();

        String localizedMessage = localizeMessage(message, request);
        err.setMessage(localizedMessage);

        if (includeStackTrace && throwable != null) {
            Map<String, ? super Object> parameters = err.getParameters();
            ExceptionalEntity exceptionalEntity = ExceptionalEntityBuilder.of(throwable).build();
            parameters.put("exception", exceptionalEntity.getException());
            parameters.put("trace", exceptionalEntity.getTrace());
            parameters.put("cause", exceptionalEntity.getCause());
        }

        return new ResponseEntity<>(err, httpStatus);
    }

    protected RestErrResult translateException(Throwable throwable, HttpServletRequest request) {
        Class<? extends Throwable> throwableType = throwable.getClass();
        ExceptionTranslator translator = determineTranslator(throwableType);
        return translator.translate(throwable)
                .setEndpoint(request.getRequestURI());
    }

    protected ExceptionTranslator determineTranslator(Class<? extends Throwable> throwableType) {
        ExceptionTranslator mappedTranslator = mappedTranslators.get(throwableType);
        if (mappedTranslator != null) {
            return mappedTranslator;
        }

        for (ExceptionTranslator translator : translators) {
            if (translator.supports(throwableType)) {
                mappedTranslators.put(throwableType, translator);
                return translator;
            }
        }

        return fallbackTranslator;
    }

    protected String localizeMessage(String message, HttpServletRequest request) {
        Locale locale = localeResolver.resolveLocale(request);

        return messageSource.getMessage(message, null, message, locale);
    }
}
