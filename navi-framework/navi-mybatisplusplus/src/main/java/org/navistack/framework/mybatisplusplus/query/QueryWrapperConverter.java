package org.navistack.framework.mybatisplusplus.query;

import org.navistack.framework.core.convert.ConversionException;
import org.navistack.framework.core.convert.Converter;
import org.navistack.framework.utils.Arrays;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class QueryWrapperConverter<T> implements Converter<T, QueryWrapper<T>> {
    @Override
    public QueryWrapper<T> convert(T src) {
        if (src == null) {
            return new QueryWrapper<>();
        }

        try {
            QueryWrapper<T> wrapper = new QueryWrapper<>(src);
            Class<?> entityClass = src.getClass();
            if (entityClass.getAnnotation(QueryEntity.class) == null) {
                return new QueryWrapper<>(src);
            }
            Field[] declaredFields = entityClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                convertProperty(declaredField, src, wrapper);
            }
            return wrapper;
        } catch (ReflectiveOperationException e) {
            throw new ConversionException("Failed converting QueryEntity to Wrapper", e);
        }
    }

    public void convertProperty(Field field, T entity, QueryWrapper<T> wrapper) throws ReflectiveOperationException {
        QueryCondition condition = field.getAnnotation(QueryCondition.class);
        // Ignore the property if it was not annotated with QueryCondition
        if (condition == null) {
            return;
        }
        Operator operator = condition.operator();
        String expression = condition.expression();

        String fieldName = field.getName();
        String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method getter = entity.getClass().getMethod(getterName);
        Object fieldValue = getter.invoke(entity);

        QueryColumn column = field.getAnnotation(QueryColumn.class);
        String columnName = column != null ? column.name() : fieldName;

        Class<?> propertyType = field.getType();

        if (fieldValue != null) {
            switch (operator) {
                case GREATER_THAN:
                    wrapper.gt(columnName, fieldValue);
                    break;
                case GREATER_THAN_OR_EQUAL_TO:
                    wrapper.ge(columnName, fieldValue);
                    break;
                case LESS_THAN:
                    wrapper.lt(columnName, fieldValue);
                    break;
                case LESS_THAN_OR_EQUAL_TO:
                    wrapper.le(columnName, fieldValue);
                    break;
                case NOT_EQUAL_TO:
                    wrapper.ne(columnName, fieldValue);
                    break;
                case IS_NULL_OR_NOT_EQUAL_TO:
                    wrapper.nested(w -> w
                            .isNull(columnName)
                            .or()
                            .ne(columnName, fieldValue));
                    break;
                case IS_NOT_NULL_AND_EQUAL_TO:
                    wrapper.nested(w -> w
                            .isNotNull(columnName)
                            .eq(columnName, fieldValue));
                    break;
                case BETWEEN_IN: {
                    Object[] fieldValues = extractElements(fieldValue);
                    wrapper.between(columnName, Arrays.get(fieldValues, 0), Arrays.get(fieldValues, 1));
                }
                break;
                case NOT_BETWEEN_IN: {
                    Object[] fieldValues = extractElements(fieldValue);
                    wrapper.notBetween(columnName, Arrays.get(fieldValues, 0), Arrays.get(fieldValues, 1));
                }
                break;
                case IN:
                    wrapper.in(columnName, extractElements(fieldValue));
                    break;
                case NOT_IN:
                    wrapper.notIn(columnName, extractElements(fieldValue));
                    break;
                case LIKE:
                    wrapper.like(columnName, fieldValue);
                    break;
                case LEFT_LIKE:
                    wrapper.likeLeft(columnName, fieldValue);
                    break;
                case RIGHT_LIKE:
                    wrapper.likeRight(columnName, fieldValue);
                    break;
                case REGEXP:
                    applyExpression(wrapper, columnName, "{0} REGEXP {1}", fieldValue);
                    break;
                case NOT_REGEXP:
                    applyExpression(wrapper, columnName, "{0} NOT REGEXP {1}", fieldValue);
                    break;
                case NOT_LIKE:
                    wrapper.notLike(columnName, fieldValue);
                    break;
                case EXPRESSION:
                    applyExpression(wrapper, columnName, expression, fieldValue);
                    break;
                case EQUAL_TO:
                default:
                    wrapper.eq(columnName, fieldValue);
                    break;
            }
        }
    }

    protected static <T> void applyExpression(QueryWrapper<T> wrapper, String columnName, String expression, Object value) {
        wrapper.apply(expression.replace("{0}", columnName), Arrays.shift(extractElements(value), 1));
    }

    protected static Object[] extractElements(Object value) {
        if (value instanceof Collection<?>) {
            return ((Collection<?>) value).toArray();
        } else if (value instanceof Iterable<?>) {
            return extractElementsFromIterator(((Iterable<?>) value).iterator());
        } else if (value instanceof Iterator<?>) {
            return extractElementsFromIterator((Iterator<?>) value);
        } else if (value instanceof Object[]) {
            return (Object[]) value;
        } else {
            return new Object[]{value};
        }
    }

    protected static Object[] extractElementsFromIterator(Iterator<?> iterator) {
        if (!iterator.hasNext()) {
            return new Object[]{};
        }
        ArrayList<Object> arrayList = new ArrayList<>(10);
        while (iterator.hasNext()) {
            arrayList.add(iterator.next());
        }
        return arrayList.toArray();
    }
}
