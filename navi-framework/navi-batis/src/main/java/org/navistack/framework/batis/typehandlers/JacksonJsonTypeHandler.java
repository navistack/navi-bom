package org.navistack.framework.batis.typehandlers;

import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import tools.jackson.databind.ObjectMapper;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Getter
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

    public void setObjectMapper(ObjectMapper objectMapper) {
        if (objectMapper == null) {
            throw new NullPointerException("objectMapper must not be null");
        }
        this.objectMapper = objectMapper;
    }

    @Override
    @SneakyThrows
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) {
        ps.setString(i, objectMapper.writeValueAsString(parameter));
    }

    @Override
    @SneakyThrows
    public E getNullableResult(ResultSet rs, String columnName) {
        String value = rs.getString(columnName);
        if (value == null) {
            return null;
        }
        return objectMapper.readValue(value, type);
    }

    @Override
    @SneakyThrows
    public E getNullableResult(ResultSet rs, int columnIndex) {
        String value = rs.getString(columnIndex);
        if (value == null) {
            return null;
        }
        return objectMapper.readValue(value, type);
    }

    @Override
    @SneakyThrows
    public E getNullableResult(CallableStatement cs, int columnIndex) {
        String value = cs.getString(columnIndex);
        if (value == null) {
            return null;
        }
        return objectMapper.readValue(value, type);
    }
}
