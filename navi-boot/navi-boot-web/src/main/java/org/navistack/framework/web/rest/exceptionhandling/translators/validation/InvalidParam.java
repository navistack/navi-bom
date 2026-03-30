package org.navistack.framework.web.rest.exceptionhandling.translators.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class InvalidParam {
    private String name;
    private String reason;
}
