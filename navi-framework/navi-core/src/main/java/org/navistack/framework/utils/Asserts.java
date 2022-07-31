package org.navistack.framework.utils;

import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.Map;
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
     * Assert that an object is null.
     *
     * @param object the object to check
     * @param exceptionSupplier a supplier for the exception to use if the assertion fails
     */
    public void isNull(Object object, Supplier<RuntimeException> exceptionSupplier) {
        state(object == null, exceptionSupplier);
    }

    /**
     * Assert that an object is not null.
     *
     * @param object the object to check
     * @param exceptionSupplier a supplier for the exception to use if the assertion fails
     */
    public void notNull(Object object, Supplier<RuntimeException> exceptionSupplier) {
        state(object != null, exceptionSupplier);
    }

    /**
     * Assert that an object equals to another one.
     *
     * @param left the object to check
     * @param right the other object to check
     * @param exceptionSupplier a supplier for the exception to use if the assertion fails
     */
    public void doesEqual(Object left, Object right, Supplier<RuntimeException> exceptionSupplier) {
        state(
                left != null && right != null && left.equals(right) && right.equals(left),
                exceptionSupplier
        );
    }

    /**
     * Assert that a string is empty.
     *
     * @param string the object to check
     * @param exceptionSupplier a supplier for the exception to use if the assertion fails
     */
    public void notEmpty(String string, Supplier<RuntimeException> exceptionSupplier) {
        state(string != null && !"".equals(string), exceptionSupplier);
    }

    /**
     * Assert that a collection is empty.
     *
     * @param collection the object to check
     * @param exceptionSupplier a supplier for the exception to use if the assertion fails
     */
    public void notEmpty(Collection<?> collection, Supplier<RuntimeException> exceptionSupplier) {
        state(collection != null && !collection.isEmpty(), exceptionSupplier);
    }

    /**
     * Assert that a map is empty.
     *
     * @param map the object to check
     * @param exceptionSupplier a supplier for the exception to use if the assertion fails
     */
    public void notEmpty(Map<?, ?> map, Supplier<RuntimeException> exceptionSupplier) {
        state(map != null && !map.isEmpty(), exceptionSupplier);
    }

    /**
     * Assert that a array is empty.
     *
     * @param array the object to check
     * @param exceptionSupplier a supplier for the exception to use if the assertion fails
     */
    public void notEmpty(Object[] array, Supplier<RuntimeException> exceptionSupplier) {
        state(array != null && array.length > 0, exceptionSupplier);
    }
}
