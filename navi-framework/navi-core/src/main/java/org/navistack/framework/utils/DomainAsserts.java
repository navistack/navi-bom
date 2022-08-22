package org.navistack.framework.utils;

import lombok.experimental.UtilityClass;
import org.navistack.framework.core.domain.DomainException;

import java.util.Collection;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;

@UtilityClass
public class DomainAsserts {
    /**
     * Assert a boolean expression, throwing an {@link DomainException}
     */
    public void state(boolean expression, Supplier<? extends DomainException> supplier) {
        if (!expression) {
            throw supplier.get();
        }
    }

    /**
     * Assert a boolean expression, throwing an {@link DomainException()}
     */
    public void state(boolean expression, int errorCode) {
        if (!expression) {
            throw new DomainException(errorCode);
        }
    }

    /**
     * Assert a boolean expression, throwing an {@link DomainException}
     */
    public void state(BooleanSupplier expression, Supplier<? extends DomainException> supplier) {
        if (!expression.getAsBoolean()) {
            throw supplier.get();
        }
    }

    /**
     * Assert a boolean expression, throwing an {@link DomainException()}
     */
    public void state(BooleanSupplier expression, int errorCode) {
        if (!expression.getAsBoolean()) {
            throw new DomainException(errorCode);
        }
    }

    /**
     * Assert a boolean expression, throwing an {@link DomainException}
     */
    public <T> void state(T object, Predicate<T> predicate, Supplier<? extends DomainException> supplier) {
        if (!predicate.test(object)) {
            throw supplier.get();
        }
    }

    /**
     * Assert a boolean expression, throwing an {@link DomainException()}
     */
    public <T> void state(T object, Predicate<T> predicate, int errorCode) {
        if (!predicate.test(object)) {
            throw new DomainException(errorCode);
        }
    }

    /**
     * Assert a boolean expression, throwing an {@link DomainException}
     */
    public <T, U> void state(T left, U right, BiPredicate<T, U> predicate, Supplier<? extends DomainException> supplier) {
        if (!predicate.test(left, right)) {
            throw supplier.get();
        }
    }

    /**
     * Assert a boolean expression, throwing an {@link DomainException()}
     */
    public <T, U> void state(T left, U right, BiPredicate<T, U> predicate, int errorCode) {
        if (!predicate.test(left, right)) {
            throw new DomainException(errorCode);
        }
    }
}
