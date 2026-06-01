package org.navistack.framework.core;

import lombok.experimental.Accessors;

@Accessors(fluent = true)
public record ErrCodeImpl(int value, ErrCategory category) implements ErrCode {

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof ErrCode)) {
            return false;
        }

        if (!category().equals(((ErrCode) other).category())) {
            return false;
        }

        return value() == ((ErrCode) other).value();
    }
}
