package org.navistack.framework.core;

public interface BuilderFactory<T> {
    Builder<T> getBuilder();
}
