package org.navistack.framework.core;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class ErrCodeImpl implements ErrCode {
    private final int value;

    private final ErrCategory category;

    public ErrCodeImpl(int value, ErrCategory category) {
        this.value = value;
        this.category = category;
    }

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
