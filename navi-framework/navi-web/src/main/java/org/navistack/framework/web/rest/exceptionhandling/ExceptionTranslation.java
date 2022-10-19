package org.navistack.framework.web.rest.exceptionhandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.navistack.framework.web.rest.RestResult;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor(staticName = "of")
public class ExceptionTranslation {
    private RestResult.ParameterizedError error;
    private HttpStatus httpStatus;
}
