package org.navistack.framework.utils;

import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;

@UtilityClass
public class Asserts {
    /**
     * Assert a boolean expression, throwing an {@link RuntimeException}
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
     * Assert a boolean expression, throwing an {@link IllegalArgumentException}
     *
     * @param expression a boolean expression
     * @param message a message to be attached to exception thrown
     */
    public void state(boolean expression, String message) {
        state(expression, () -> new IllegalArgumentException(message));
    }

    /**
     * Assert a boolean expression, throwing an {@link RuntimeException}
     *
     * @param expression a boolean expression supplier
     * @param exceptionSupplier a supplier for the exception to use if the assertion fails
     */
    public void state(BooleanSupplier expression, Supplier<RuntimeException> exceptionSupplier) {
        state(expression.getAsBoolean(), exceptionSupplier);
    }

    /**
     * Assert a boolean expression, throwing an {@link IllegalArgumentException}
     *
     * @param expression a boolean expression supplier
     * @param message a message to be attached to exception thrown
     */
    public void state(BooleanSupplier expression, String message) {
        state(expression, () -> new IllegalArgumentException(message));
    }

    /**
     * Assert a boolean expression, throwing an {@link RuntimeException}
     *
     * @param object the object to test with
     * @param predicate the predicate to execute
     * @param exceptionSupplier a supplier for the exception to use if the assertion fails
     */
    public <T> void state(T object, Predicate<T> predicate, Supplier<RuntimeException> exceptionSupplier) {
        state(predicate.test(object), exceptionSupplier);
    }

    /**
     * Assert a boolean expression, throwing an {@link IllegalArgumentException}
     *
     * @param object the object to test with
     * @param predicate the predicate to execute
     * @param message a message to be attached to exception thrown
     */
    public <T> void state(T object, Predicate<T> predicate, String message) {
        state(object, predicate, () -> new IllegalArgumentException(message));
    }

    /**
     * Assert a boolean expression, throwing an {@link RuntimeException}
     *
     * @param left the object to test with
     * @param right the other object to test with
     * @param predicate the predicate to execute
     * @param exceptionSupplier a supplier for the exception to use if the assertion fails
     */
    public <T, U> void state(T left, U right, BiPredicate<T, U> predicate, Supplier<RuntimeException> exceptionSupplier) {
        state(predicate.test(left, right), exceptionSupplier);
    }

    /**
     * Assert a boolean expression, throwing an {@link IllegalArgumentException}
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
     * Assert that an object equals to another one.
     *
     * @param left the object to check
     * @param right the other object to check
     * @param exceptionSupplier a supplier for the exception to use if the assertion fails
     */
    @Deprecated
    public void doesEqual(Object left, Object right, Supplier<RuntimeException> exceptionSupplier) {
        state(left, right, Objects::equals, exceptionSupplier);
    }

    /**
     * Assert that a string is empty.
     *
     * @param string the object to check
     * @param exceptionSupplier a supplier for the exception to use if the assertion fails
     */
    @Deprecated
    public void notEmpty(String string, Supplier<RuntimeException> exceptionSupplier) {
        state(string, Strings::hasLength, exceptionSupplier);
    }

    /**
     * Assert that a collection is empty.
     *
     * @param collection the object to check
     * @param exceptionSupplier a supplier for the exception to use if the assertion fails
     */
    @Deprecated
    public void notEmpty(Collection<?> collection, Supplier<RuntimeException> exceptionSupplier) {
        state(collection, Collections::isNotEmpty, exceptionSupplier);
    }

    /**
     * Assert that a map is empty.
     *
     * @param map the object to check
     * @param exceptionSupplier a supplier for the exception to use if the assertion fails
     */
    @Deprecated
    public void notEmpty(Map<?, ?> map, Supplier<RuntimeException> exceptionSupplier) {
        state(map, Maps::isNotEmpty, exceptionSupplier);
    }

    /**
     * Assert that a array is empty.
     *
     * @param array the object to check
     * @param exceptionSupplier a supplier for the exception to use if the assertion fails
     */
    @Deprecated
    public void notEmpty(Object[] array, Supplier<RuntimeException> exceptionSupplier) {
        state(array, Arrays::isNotEmpty, exceptionSupplier);
    }
}
