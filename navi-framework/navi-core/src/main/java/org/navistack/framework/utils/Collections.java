package org.navistack.framework.utils;

import lombok.experimental.UtilityClass;

import java.util.Collection;

@UtilityClass
public class Collections {
    public <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public <T> boolean isNotEmpty(Collection<T> collection) {
        return collection != null && !collection.isEmpty();
    }
}
