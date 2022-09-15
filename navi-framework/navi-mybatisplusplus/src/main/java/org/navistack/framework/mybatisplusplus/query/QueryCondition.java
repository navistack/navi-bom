package org.navistack.framework.mybatisplusplus.query;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryCondition {
    Operator operator() default Operator.EQUAL_TO;
    String expression() default "";
}
