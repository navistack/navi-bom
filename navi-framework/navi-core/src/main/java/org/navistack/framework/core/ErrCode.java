package org.navistack.framework.core;

public interface ErrCode {
    int value();

    ErrCategory category();

    default String message() {
        return category()
                .message(value());
    }
}
