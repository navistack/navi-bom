package org.navistack.framework.utils;

import lombok.experimental.UtilityClass;

import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;

@UtilityClass
public class Asserts {
    /**
     * Assert a boolean expression, throwing an {@link RuntimeException}.
     *
     * @param expression a boolean expression
     * @param exceptionSupplier a supplier for the exception to use if the assertion fails
     */
    public void state(boolean expression, Supplier<RuntimeException> exceptionSupplier) {
        if (!expression) {
            throw exceptionSupplier.get();
        }
    }

    /**
     * Assert a boolean expression, throwing an {@link IllegalArgumentException}.
     *
     * @param expression a boolean expression
     * @param message a message to be attached to exception thrown
     */
    public void state(boolean expression, String message) {
        state(expression, () -> new IllegalArgumentException(message));
    }

    /**
     * Assert a boolean expression, throwing an {@link RuntimeException}.
     *
     * @param expression a boolean expression supplier
     * @param exceptionSupplier a supplier for the exception to use if the assertion fails
     */
    public void state(BooleanSupplier expression, Supplier<RuntimeException> exceptionSupplier) {
        state(expression.getAsBoolean(), exceptionSupplier);
    }

    /**
     * Assert a boolean expression, throwing an {@link IllegalArgumentException}.
     *
     * @param expression a boolean expression supplier
     * @param message a message to be attached to exception thrown
     */
    public void state(BooleanSupplier expression, String message) {
        state(expression, () -> new IllegalArgumentException(message));
    }

    /**
     * Assert a boolean expression, throwing an {@link RuntimeException}.
     *
     * @param object the object to test with
     * @param predicate the predicate to execute
     * @param exceptionSupplier a supplier for the exception to use if the assertion fails
     */
    public <T> void state(T object, Predicate<T> predicate, Supplier<RuntimeException> exceptionSupplier) {
        state(predicate.test(object), exceptionSupplier);
    }

    /**
     * Assert a boolean expression, throwing an {@link IllegalArgumentException}.
     *
     * @param object the object to test with
     * @param predicate the predicate to execute
     * @param message a message to be attached to exception thrown
     */
    public <T> void state(T object, Predicate<T> predicate, String message) {
        state(object, predicate, () -> new IllegalArgumentException(message));
    }

    /**
     * Assert a boolean expression, throwing an {@link RuntimeException}.
     *
     * @param left the object to test with
     * @param right the other object to test with
     * @param predicate the predicate to execute
     * @param exceptionSupplier a supplier for the exception to use if the assertion fails
     */
    public <T, U> void state(T left,
                             U right, BiPredicate<T, U> predicate,
                             Supplier<RuntimeException> exceptionSupplier) {
        state(predicate.test(left, right), exceptionSupplier);
    }

    /**
     * Assert a boolean expression, throwing an {@link IllegalArgumentException}.
     *
     * @param left the object to test with
     * @param right the other object to test with
     * @param predicate the predicate to execute
     * @param message a message to be attached to exception thrown
     */
    public <T, U> void state(T left, U right, BiPredicate<T, U> predicate, String message) {
        state(left, right, predicate, () -> new IllegalArgumentException(message));
    }

    /**
     * Assert that an object is null.
     *
     * @param object the object to check
     * @param exceptionSupplier a supplier for the exception to use if the assertion fails
     */
    public <T> void isNull(T object, Supplier<RuntimeException> exceptionSupplier) {
        state(object, Objects::isNull, exceptionSupplier);
    }

    /**
     * Assert that an object is not null.
     *
     * @param object the object to check
     * @param exceptionSupplier a supplier for the exception to use if the assertion fails
     */
    public <T> void notNull(T object, Supplier<RuntimeException> exceptionSupplier) {
        state(object, Objects::isNotNull, exceptionSupplier);
    }

    /**
     * Assert that an object is not null, throwing an {@link NullPointerException}.
     *
     * @param object the object to check
     * @param message a message to be attached to exception thrown
     */
    public <T> void notNull(T object, String message) {
        state(object, Objects::isNotNull, () -> new NullPointerException(message));
    }
}
