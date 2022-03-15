package org.navistack.framework.mybatisplusplus.typehandlers;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.navistack.framework.core.NumericEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnumNumericTypeHandler<E extends Enum<E> & NumericEnum> extends BaseTypeHandler<E> {

    private final Class<E> type;

    public EnumNumericTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.number());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int number = rs.getInt(columnName);
        if (number == 0 && rs.wasNull()) {
            return null;
        }
        return toNumericEnum(number);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int number = rs.getInt(columnIndex);
        if (number == 0 && rs.wasNull()) {
            return null;
        }
        return toNumericEnum(number);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int number = cs.getInt(columnIndex);
        if (number == 0 && cs.wasNull()) {
            return null;
        }
        return toNumericEnum(number);
    }

    private E toNumericEnum(int number) {
        try {
            return NumericEnum.valueOf(type, number);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot convert " + number + " to " + type.getSimpleName() + " by numeric value.", ex);
        }
    }
}
