package org.navistack.boot.autoconfigure.mybatis;

import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.navistack.framework.core.NumericEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnumDelegatingTypeHandler<E extends Enum<E>> implements TypeHandler<E> {
    private final TypeHandler<E> typeHandler;

    @SuppressWarnings({"rawtypes", "unchecked"})
    public EnumDelegatingTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        typeHandler = type.isAssignableFrom(NumericEnum.class)
                ? new EnumNumericTypeHandler(type)
                : new EnumTypeHandler<>(type)
                ;
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        typeHandler.setParameter(ps, i, parameter, jdbcType);
    }

    @Override
    public E getResult(ResultSet rs, String columnName) throws SQLException {
        return typeHandler.getResult(rs, columnName);
    }

    @Override
    public E getResult(ResultSet rs, int columnIndex) throws SQLException {
        return typeHandler.getResult(rs, columnIndex);
    }

    @Override
    public E getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return typeHandler.getResult(cs, columnIndex);
    }
}
