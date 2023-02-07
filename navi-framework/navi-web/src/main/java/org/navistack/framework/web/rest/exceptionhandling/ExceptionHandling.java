package org.navistack.framework.web.rest.exceptionhandling;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.navistack.framework.web.rest.ExceptionalEntity;
import org.navistack.framework.web.rest.ExceptionalEntityBuilder;
import org.navistack.framework.web.rest.RestResult;
import org.navistack.framework.web.rest.exceptionhandling.translators.general.ThrowableTranslator;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.LocaleResolver;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestControllerAdvice
public class ExceptionHandling {
    private static final String ERROR_MESSAGE_KEY_PREFIX = "errors.coded.";

    @Getter
    @Setter
    private boolean includeStackTrace = false;

    @Getter
    @Setter
    @NonNull
    private MessageSource messageSource;

    @Getter
    @Setter
    @NonNull
    private LocaleResolver localeResolver;

    @Getter
    @Setter
    @NonNull
    private ExceptionTranslator fallbackTranslator = new ThrowableTranslator();

    @Getter
    private final Collection<ExceptionTranslator> translators = new LinkedList<>();

    @Getter
    private final Map<Class<?>, ExceptionTranslator> mappedTranslators = new ConcurrentHashMap<>();

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
    public ResponseEntity<RestResult.Err<? super RestResult.ParameterizedError>> handleThrowable(
            Throwable throwable,
            HttpServletRequest request
    ) {
        Class<? extends Throwable> throwableType = throwable.getClass();
        ExceptionTranslator translator = determineTranslator(throwableType);
        ExceptionTranslation translation = translator.translate(throwable);
        return buildResponseEntity(
                throwable,
                request,
                translation.getError(),
                translation.getHttpStatus()
        );
    }

    protected <E extends RestResult.ParameterizedError>
    ResponseEntity<RestResult.Err<? super E>> buildResponseEntity(
            Throwable throwable,
            HttpServletRequest request,
            E error,
            HttpStatus httpStatus
    ) {
        if (httpStatus.is4xxClientError()) {
            log.warn("{}: {}", httpStatus.getReasonPhrase(), throwable.getMessage());
        } else if (httpStatus.is5xxServerError()) {
            log.error(httpStatus.getReasonPhrase(), throwable);
        }

        int errorCode = error.getError();
        String message = error.getMessage();

        String localizedMessage = localizeMessage(errorCode, message, request);
        error.setMessage(localizedMessage);

        if (includeStackTrace) {
            Map<String, ? super Object> parameters = error.getParameters();
            ExceptionalEntity exceptionalEntity = ExceptionalEntityBuilder.of(throwable).build();
            parameters.put("exception", exceptionalEntity.getException());
            parameters.put("trace", exceptionalEntity.getTrace());
            parameters.put("cause", exceptionalEntity.getCause());
        }

        return new ResponseEntity<>(RestResult.err(error), httpStatus);
    }

    protected ExceptionTranslator determineTranslator(Class<? extends Throwable> throwableType) {
        ExceptionTranslator translator = mappedTranslators.get(throwableType);
        if (translator != null) {
            return translator;
        }

        Iterator<ExceptionTranslator> translatorIterator = translators.iterator();
        while (translatorIterator.hasNext()) {
            translator = translatorIterator.next();
            if (translator.supports(throwableType)) {
                mappedTranslators.put(throwableType, translator);
                return translator;
            }
        }

        return fallbackTranslator;
    }

    protected String localizeMessage(int errorCode, String message, HttpServletRequest request) {
        Locale locale = localeResolver.resolveLocale(request);

        String errorMessageKey = ERROR_MESSAGE_KEY_PREFIX + errorCode;
        String localizedMessage = messageSource.getMessage(errorMessageKey, null, null, locale);
        if (localizedMessage != null) {
            return localizedMessage;
        }

        localizedMessage = messageSource.getMessage(message, null, null, locale);
        if (localizedMessage != null) {
            return localizedMessage;
        }

        return message;
    }
}
