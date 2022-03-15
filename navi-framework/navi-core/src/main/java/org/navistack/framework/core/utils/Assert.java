package org.navistack.framework.core.utils;

import lombok.experimental.UtilityClass;
import org.navistack.framework.core.problem.GenericThrowableProblem;
import org.navistack.framework.core.problem.ThrowableProblem;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

@UtilityClass
public class Assert {
    /**
     * Assert a boolean expression, throwing an {@code ThrowableProblem}
     *
     * @param expression a boolean expression
     * @param problemSupplier a supplier for the throwable problem to use if the assertion fails
     */
    public void state(boolean expression, Supplier<ThrowableProblem> problemSupplier) {
        if (!expression) {
            throw problemSupplier.get();
        }
    }

    /**
     * Assert a boolean expression, throwing an {@code ThrowableProblem}
     *
     * @param expression a boolean expression
     * @param message the exception message to use if the assertion fails
     */
    public void state(boolean expression, String message) {
        state(expression, () -> new GenericThrowableProblem(message));
    }

    /**
     * Assert that an object is null.
     *
     * @param object the object to check
     * @param problemSupplier a supplier for the throwable problem to use if the assertion fails
     */
    public void isNull(Object object, Supplier<ThrowableProblem> problemSupplier) {
        state(object == null, problemSupplier);
    }

    /**
     * Assert that an object is null.
     *
     * @param object the object to check
     * @param message the exception message to use if the assertion fails
     */
    public void isNull(Object object, String message) {
        isNull(object, () -> new GenericThrowableProblem(message));
    }

    /**
     * Assert that an object is not null.
     *
     * @param object the object to check
     * @param problemSupplier a supplier for the throwable problem to use if the assertion fails
     */
    public void notNull(Object object, Supplier<ThrowableProblem> problemSupplier) {
        state(object != null, problemSupplier);
    }

    /**
     * Assert that an object is not null.
     *
     * @param object the object to check
     * @param message the exception message to use if the assertion fails
     */
    public void notNull(Object object, String message) {
        notNull(object, () -> new GenericThrowableProblem(message));
    }

    /**
     * Assert that an object equals to another one.
     *
     * @param left the object to check
     * @param right the other object to check
     * @param problemSupplier a supplier for the throwable problem to use if the assertion fails
     */
    public void doesEqual(Object left, Object right, Supplier<ThrowableProblem> problemSupplier) {
        state(
                left != null && right != null && left.equals(right) && right.equals(left),
                problemSupplier
        );
    }

    /**
     * Assert that an object equals to another one.
     *
     * @param left the object to check
     * @param right the other object to check
     * @param message the exception message to use if the assertion fails
     */
    public void doesEqual(Object left, Object right, String message) {
        doesEqual(left, right, () -> new GenericThrowableProblem(message));
    }

    /**
     * Assert that a string is empty.
     *
     * @param string the object to check
     * @param problemSupplier a supplier for the throwable problem to use if the assertion fails
     */
    public void notEmpty(String string, Supplier<ThrowableProblem> problemSupplier) {
        state(string != null && !"".equals(string), problemSupplier);
    }

    /**
     * Assert that a string is empty.
     *
     * @param string the object to check
     * @param message the exception message to use if the assertion fails
     */
    public void notEmpty(String string, String message) {
        notEmpty(string, () -> new GenericThrowableProblem(message));
    }

    /**
     * Assert that a collection is empty.
     *
     * @param collection the object to check
     * @param problemSupplier a supplier for the throwable problem to use if the assertion fails
     */
    public void notEmpty(Collection<?> collection, Supplier<ThrowableProblem> problemSupplier) {
        state(collection != null && !collection.isEmpty(), problemSupplier);
    }

    /**
     * Assert that a collection is empty.
     *
     * @param collection the object to check
     * @param message the exception message to use if the assertion fails
     */
    public void notEmpty(Collection<?> collection, String message) {
        notEmpty(collection, () -> new GenericThrowableProblem(message));
    }

    /**
     * Assert that a map is empty.
     *
     * @param map the object to check
     * @param problemSupplier a supplier for the throwable problem to use if the assertion fails
     */
    public void notEmpty(Map<?, ?> map, Supplier<ThrowableProblem> problemSupplier) {
        state(map != null && !map.isEmpty(), problemSupplier);
    }

    /**
     * Assert that a map is empty.
     *
     * @param map the object to check
     * @param message the exception message to use if the assertion fails
     */
    public void notEmpty(Map<?, ?> map, String message) {
        notEmpty(map, () -> new GenericThrowableProblem(message));
    }

    /**
     * Assert that a array is empty.
     *
     * @param array the object to check
     * @param problemSupplier a supplier for the throwable problem to use if the assertion fails
     */
    public void notEmpty(Object[] array, Supplier<ThrowableProblem> problemSupplier) {
        state(array != null && array.length > 0, problemSupplier);
    }

    /**
     * Assert that a array is empty.
     *
     * @param array the object to check
     * @param message the exception message to use if the assertion fails
     */
    public void notEmpty(Object[] array, String message) {
        notEmpty(array, () -> new GenericThrowableProblem(message));
    }
}
