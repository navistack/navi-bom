package org.navistack.framework.batis.typehandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JacksonJsonTypeHandler<E> extends BaseTypeHandler<E> {
    private final Class<E> type;

    private ObjectMapper objectMapper;

    public JacksonJsonTypeHandler(Class<E> type) {
        if (type == null) {
            throw new NullPointerException("Type argument must not be null");
        }
        this.type = type;
        this.objectMapper = new ObjectMapper();
    }

    public Class<E> getType() {
        return type;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        if (objectMapper == null) {
            throw new NullPointerException("objectMapper must not be null");
        }
        this.objectMapper = objectMapper;
    }

    @Override
    @SneakyThrows
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, objectMapper.writeValueAsString(parameter));
    }

    @Override
    @SneakyThrows
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        if (value == null) {
            return null;
        }
        return objectMapper.readValue(value, type);
    }

    @Override
    @SneakyThrows
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        if (value == null) {
            return null;
        }
        return objectMapper.readValue(value, type);
    }

    @Override
    @SneakyThrows
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        if (value == null) {
            return null;
        }
        return objectMapper.readValue(value, type);
    }
}
