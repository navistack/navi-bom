package org.navistack.framework.mybatisplusplus.utils;

import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import org.navistack.framework.mybatisplusplus.query.QueryWrapper;

import java.util.Collections;
import java.util.Map;

class EmptyQueryWrapper<T> extends QueryWrapper<T> {

    @Override
    public T getEntity() {
        return null;
    }

    @Override
    public EmptyQueryWrapper<T> setEntity(T entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public QueryWrapper<T> setEntityClass(Class<T> entityClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<T> getEntityClass() {
        return null;
    }

    @Override
    public String getSqlSelect() {
        return null;
    }

    @Override
    public MergeSegments getExpression() {
        return null;
    }

    @Override
    public boolean isEmptyOfWhere() {
        return true;
    }

    @Override
    public boolean isEmptyOfNormal() {
        return true;
    }

    @Override
    public boolean nonEmptyOfEntity() {
        return !isEmptyOfEntity();
    }

    @Override
    public boolean isEmptyOfEntity() {
        return true;
    }

    @Override
    protected void initNeed() {
    }

    @Override
    public EmptyQueryWrapper<T> last(boolean condition, String lastSql) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getSqlSegment() {
        return null;
    }

    @Override
    public Map<String, Object> getParamNameValuePairs() {
        return Collections.emptyMap();
    }

    @Override
    protected String columnsToString(String... columns) {
        return null;
    }

    @Override
    protected String columnToString(String column) {
        return null;
    }

    @Override
    protected EmptyQueryWrapper<T> instance() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
}
