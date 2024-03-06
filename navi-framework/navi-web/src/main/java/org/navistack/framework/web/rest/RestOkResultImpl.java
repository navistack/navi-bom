package org.navistack.framework.web.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestOkResultImpl<T> implements RestOkResult<T> {
    private T result;
}
