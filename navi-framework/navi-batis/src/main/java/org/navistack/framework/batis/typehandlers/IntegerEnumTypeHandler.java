package org.navistack.framework.batis.typehandlers;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.navistack.framework.core.Numeric;
import org.navistack.framework.utils.Enums;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IntegerEnumTypeHandler<E extends Enum<E> & Numeric<Integer>> extends BaseTypeHandler<E> {

    private final Class<E> type;

    public IntegerEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.numeral());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int n = rs.getInt(columnName);
        if (n == 0 && rs.wasNull()) {
            return null;
        }
        return toNumericEnum(n);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int n = rs.getInt(columnIndex);
        if (n == 0 && rs.wasNull()) {
            return null;
        }
        return toNumericEnum(n);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int n = cs.getInt(columnIndex);
        if (n == 0 && cs.wasNull()) {
            return null;
        }
        return toNumericEnum(n);
    }

    private E toNumericEnum(int n) {
        try {
            return Enums.valueOf(type, n);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot convert " + n + " to " + type.getSimpleName() + " by numeric value.", ex);
        }
    }
}
