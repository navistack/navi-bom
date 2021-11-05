package org.navistack.boot.autoconfigure.web.rest.exceptionhandling.common;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@UtilityClass
@Slf4j
public class AdviceTraits {
    public void log(Throwable throwable, HttpStatus httpStatus) {
        if (httpStatus.is4xxClientError()) {
            log.warn("{}: {}", httpStatus.getReasonPhrase(), throwable.getMessage());
        } else if (httpStatus.is5xxServerError()) {
            log.error(httpStatus.getReasonPhrase(), throwable);
        }
    }
}
