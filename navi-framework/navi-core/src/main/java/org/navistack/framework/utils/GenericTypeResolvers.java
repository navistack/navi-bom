package org.navistack.framework.utils;

import lombok.experimental.UtilityClass;
import org.springframework.core.GenericTypeResolver;

@UtilityClass
public class GenericTypeResolvers {
    public Class<?> resolveTypeArgumentsOf(Class<?> clazz, Class<?> genericIfc, int idx) {
        return Arrays.get(GenericTypeResolver.resolveTypeArguments(clazz, genericIfc), idx);
    }
}
