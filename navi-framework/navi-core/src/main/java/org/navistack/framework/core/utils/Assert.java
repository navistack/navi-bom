package org.navistack.framework.core.utils;

import lombok.experimental.UtilityClass;
import org.navistack.framework.core.problem.Problem;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

@UtilityClass
public class Assert {
    /**
     * Assert a boolean expression, throwing an {@link Problem}
     *
     * @param expression a boolean expression
     * @param problemSupplier a supplier for the problem to use if the assertion fails
     */
    public void state(boolean expression, Supplier<Problem> problemSupplier) {
        if (!expression) {
            throw problemSupplier.get();
        }
    }

    /**
     * Assert that an object is null.
     *
     * @param object the object to check
     * @param problemSupplier a supplier for the problem to use if the assertion fails
     */
    public void isNull(Object object, Supplier<Problem> problemSupplier) {
        state(object == null, problemSupplier);
    }

    /**
     * Assert that an object is not null.
     *
     * @param object the object to check
     * @param problemSupplier a supplier for the problem to use if the assertion fails
     */
    public void notNull(Object object, Supplier<Problem> problemSupplier) {
        state(object != null, problemSupplier);
    }

    /**
     * Assert that an object equals to another one.
     *
     * @param left the object to check
     * @param right the other object to check
     * @param problemSupplier a supplier for the problem to use if the assertion fails
     */
    public void doesEqual(Object left, Object right, Supplier<Problem> problemSupplier) {
        state(
                left != null && right != null && left.equals(right) && right.equals(left),
                problemSupplier
        );
    }

    /**
     * Assert that a string is empty.
     *
     * @param string the object to check
     * @param problemSupplier a supplier for the problem to use if the assertion fails
     */
    public void notEmpty(String string, Supplier<Problem> problemSupplier) {
        state(string != null && !"".equals(string), problemSupplier);
    }

    /**
     * Assert that a collection is empty.
     *
     * @param collection the object to check
     * @param problemSupplier a supplier for the problem to use if the assertion fails
     */
    public void notEmpty(Collection<?> collection, Supplier<Problem> problemSupplier) {
        state(collection != null && !collection.isEmpty(), problemSupplier);
    }

    /**
     * Assert that a map is empty.
     *
     * @param map the object to check
     * @param problemSupplier a supplier for the problem to use if the assertion fails
     */
    public void notEmpty(Map<?, ?> map, Supplier<Problem> problemSupplier) {
        state(map != null && !map.isEmpty(), problemSupplier);
    }

    /**
     * Assert that a array is empty.
     *
     * @param array the object to check
     * @param problemSupplier a supplier for the problem to use if the assertion fails
     */
    public void notEmpty(Object[] array, Supplier<Problem> problemSupplier) {
        state(array != null && array.length > 0, problemSupplier);
    }
}
