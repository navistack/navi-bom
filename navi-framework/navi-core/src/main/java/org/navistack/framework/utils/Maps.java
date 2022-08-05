package org.navistack.framework.utils;

import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class Maps {
    public <K, V> boolean isEmpty(Map<K, V> map) {
        return map == null || map.isEmpty();
    }

    public <K, V> boolean isNotEmpty(Map<K, V> map) {
        return map != null && !map.isEmpty();
    }
}
