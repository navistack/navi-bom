package org.navistack.framework.web.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ExceptionalEntity {
    private String exception;
    private Collection<String> trace;
    private ExceptionalEntity cause;
}
