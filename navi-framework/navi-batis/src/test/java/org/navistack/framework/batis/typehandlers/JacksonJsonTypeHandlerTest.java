package org.navistack.framework.batis.typehandlers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class JacksonJsonTypeHandlerTest extends BaseTypeHandlerTest {
    private final JacksonJsonTypeHandler<JsonObject> typeHandler = new JacksonJsonTypeHandler<>(JsonObject.class);

    private final JsonObject object = new JsonObject.JsonObjectBuilder()
            .n(0)
            .s("s0")
            .o(
                    new JsonObject.JsonObjectBuilder()
                            .n(1)
                            .s("s1")
                            .build()
            )
            .a(
                    new JsonObject[]{
                            new JsonObject.JsonObjectBuilder()
                                    .n(2)
                                    .s("s2")
                                    .build(),
                            new JsonObject.JsonObjectBuilder()
                                    .n(3)
                                    .s("s3")
                                    .build(),
                    }
            )
            .build();

    private final String json = "{\"n\":0,\"s\":\"s0\",\"o\":{\"n\":1,\"s\":\"s1\",\"o\":null,\"a\":null},\"a\":[{\"n\":2,\"s\":\"s2\",\"o\":null,\"a\":null},{\"n\":3,\"s\":\"s3\",\"o\":null,\"a\":null}]}";

    @Override
    @Test
    void shouldSetParameter() throws Exception {
        typeHandler.setParameter(ps, 1, object, null);
        verify(ps).setString(1, json);
    }

    @Override
    @Test
    void shouldGetResultFromResultSetByName() throws Exception {
        when(rs.getString("column")).thenReturn(json);
        assertThat(typeHandler.getResult(rs, "column")).isEqualTo(object);
        verify(rs, never()).wasNull();
    }

    @Override
    @Test
    void shouldGetResultNullFromResultSetByName() throws Exception {
        when(rs.getString("column")).thenReturn(null);
        assertThat(typeHandler.getResult(rs, "column")).isNull();
    }

    @Override
    @Test
    void shouldGetResultFromResultSetByPosition() throws Exception {
        when(rs.getString(1)).thenReturn(json);
        assertThat(typeHandler.getResult(rs, 1)).isEqualTo(object);
        verify(rs, never()).wasNull();
    }

    @Override
    @Test
    void shouldGetResultNullFromResultSetByPosition() throws Exception {
        when(rs.getString(1)).thenReturn(null);
        assertThat(typeHandler.getResult(rs, 1)).isNull();
    }

    @Override
    @Test
    void shouldGetResultFromCallableStatement() throws Exception {
        when(cs.getString(1)).thenReturn(json);
        assertThat(typeHandler.getResult(cs, 1)).isEqualTo(object);
        verify(rs, never()).wasNull();
    }

    @Override
    @Test
    void shouldGetResultNullFromCallableStatement() throws Exception {
        when(cs.getString(1)).thenReturn(null);
        assertThat(typeHandler.getResult(cs, 1)).isNull();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JsonObject {
        private Number n;
        private String s;
        private JsonObject o;
        private JsonObject[] a;
    }
}
