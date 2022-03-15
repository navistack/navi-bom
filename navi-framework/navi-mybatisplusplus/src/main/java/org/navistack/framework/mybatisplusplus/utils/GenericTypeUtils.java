package org.navistack.framework.mybatisplusplus.utils;

import lombok.experimental.UtilityClass;
import org.springframework.core.GenericTypeResolver;

@UtilityClass
public class GenericTypeUtils {
    public Class<?> resolveTypeArgumentsOf(Class<?> clazz, Class<?> genericIfc, int idx) {
        Class<?>[] classes = GenericTypeResolver.resolveTypeArguments(clazz, genericIfc);
        return classes == null || classes.length <= idx ? null : classes[idx];
    }
}
