package org.navistack.framework.mybatisplusplus.utils;

import lombok.experimental.UtilityClass;
import org.navistack.framework.mybatisplusplus.query.LambdaQueryWrapper;
import org.navistack.framework.mybatisplusplus.query.QueryEntity;
import org.navistack.framework.mybatisplusplus.query.QueryWrapper;
import org.navistack.framework.mybatisplusplus.query.QueryWrapperConverter;
import org.navistack.framework.mybatisplusplus.update.LambdaUpdateWrapper;
import org.navistack.framework.mybatisplusplus.update.UpdateWrapper;

@UtilityClass
public class Wrappers {
    private final EmptyQueryWrapper<?> EMPTY_QUERY_WRAPPER = new EmptyQueryWrapper<>();

    public <T> QueryWrapper<T> query() {
        return new QueryWrapper<>();
    }

    public <T> QueryWrapper<T> query(T entity) {
        if (entity == null) {
            return new QueryWrapper<>();
        } else {
            Class<?> tClass = entity.getClass();
            QueryEntity annotation = tClass.getAnnotation(QueryEntity.class);
            if (annotation == null) {
                return new QueryWrapper<>(entity);
            } else {
                return new QueryWrapperConverter<T>().convert(entity);
            }
        }
    }

    public <T> LambdaQueryWrapper<T> lambdaQuery() {
        return new LambdaQueryWrapper<>();
    }

    public <T> LambdaQueryWrapper<T> lambdaQuery(T entity) {
        return new LambdaQueryWrapper<>(entity);
    }

    public <T> LambdaQueryWrapper<T> lambdaQuery(Class<T> entityClass) {
        return new LambdaQueryWrapper<>(entityClass);
    }

    public <T> UpdateWrapper<T> update() {
        return new UpdateWrapper<>();
    }

    public <T> UpdateWrapper<T> update(T entity) {
        return new UpdateWrapper<>(entity);
    }

    public <T> LambdaUpdateWrapper<T> lambdaUpdate() {
        return new LambdaUpdateWrapper<>();
    }

    public <T> LambdaUpdateWrapper<T> lambdaUpdate(T entity) {
        return new LambdaUpdateWrapper<>(entity);
    }

    public <T> LambdaUpdateWrapper<T> lambdaUpdate(Class<T> entityClass) {
        return new LambdaUpdateWrapper<>(entityClass);
    }

    @SuppressWarnings("unchecked")
    public <T> QueryWrapper<T> emptyWrapper() {
        return (QueryWrapper<T>) EMPTY_QUERY_WRAPPER;
    }
}
