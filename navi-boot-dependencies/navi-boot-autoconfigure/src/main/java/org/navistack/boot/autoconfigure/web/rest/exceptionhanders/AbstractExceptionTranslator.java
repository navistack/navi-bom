package org.navistack.boot.autoconfigure.web.rest.exceptionhanders;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.navistack.boot.autoconfigure.web.rest.exceptionhanders.common.ExceptionHandlerTrait;
import org.navistack.framework.utils.Objects;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
public abstract class AbstractExceptionTranslator implements ExceptionHandlerTrait, InitializingBean {
    @Getter
    @Setter
    private boolean includeStackTrace = false;

    @Getter
    @Setter
    private MessageSource messageSource;

    @Getter
    @Setter
    private LocaleResolver localeResolver;

    @Override
    public <E extends RestResult.ThrowableError>
    ResponseEntity<RestResult.Err<? super E>> toResponse(E error, HttpStatus httpStatus, HttpServletRequest httpServletRequest) {
        int errorCode = error.getError();
        String message = error.getMessage();
        Map<String, ? super Object> parameters = error.getParameters();
        Throwable throwable = error.getThrowable();

        if (httpStatus.is4xxClientError()) {
            log.warn("{}: {}", httpStatus.getReasonPhrase(), throwable.getMessage());
        } else if (httpStatus.is5xxServerError()) {
            log.error(httpStatus.getReasonPhrase(), throwable);
        }

        String localizedMessage = Objects.firstNonNull(
                messageSource.getMessage(Integer.toString(errorCode), null, null, localeResolver.resolveLocale(httpServletRequest)),
                messageSource.getMessage(message, null, null, localeResolver.resolveLocale(httpServletRequest)),
                message
        );

        if (includeStackTrace) {
            return new ResponseEntity<>(RestResult.err(
                    RestResult.ExceptionalError.of(
                            errorCode,
                            localizedMessage,
                            parameters,
                            throwable
                    )
            ), httpStatus);
        } else {
            return new ResponseEntity<>(RestResult.err(
                    RestResult.ParameterizedError.of(
                            errorCode,
                            localizedMessage,
                            parameters
                    )
            ), httpStatus);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (localeResolver == null) {
            localeResolver = new AcceptHeaderLocaleResolver();
        }
        if (messageSource == null) {
            messageSource = new ReloadableResourceBundleMessageSource();
        }
    }
}
