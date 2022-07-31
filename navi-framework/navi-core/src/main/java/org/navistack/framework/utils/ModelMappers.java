package org.navistack.framework.utils;

import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.lang.reflect.Type;

@UtilityClass
public class ModelMappers {
    private final ModelMapper instance;

    static {
        instance = new ModelMapper();
        instance.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public ModelMapper instance() {
        return instance;
    }

    public <D> D map(Object source, Class<D> destinationType) {
        return instance.map(source, destinationType);
    }

    public <D> D map(Object source, Class<D> destinationType, String typeMapName) {
        return instance.map(source, destinationType, typeMapName);
    }

    public void map(Object source, Object destination) {
        instance.map(source, destination);
    }

    public void map(Object source, Object destination, String typeMapName) {
        instance.map(source, destination, typeMapName);
    }

    public <D> D map(Object source, Type destinationType) {
        return instance.map(source, destinationType);
    }

    public <D> D map(Object source, Type destinationType, String typeMapName) {
        return instance.map(source, destinationType, typeMapName);
    }
}
