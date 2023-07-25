package org.navistack.framework.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class JsonStringSerializerTest {
    private final JsonObject object = JsonObject.builder()
            .n(0)
            .s("s0")
            .o(
                    JsonObject.builder()
                            .n(1)
                            .s("s1")
                            .build()
            )
            .a(
                    new JsonObject[]{
                            JsonObject.builder()
                                    .n(2)
                                    .s("s2")
                                    .build(),
                            JsonObject.builder()
                                    .n(3)
                                    .s("s3")
                                    .build(),
                    }
            )
            .build();

    @Test
    void shouldSerializeAsJsonString() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        String expected = "{\"n\":0,\"s\":\"s0\",\"o\":{\"n\":1,\"s\":\"s1\",\"o\":null,\"a\":null},\"a\":\"[{\\\"n\\\":2,\\\"s\\\":\\\"s2\\\",\\\"o\\\":null,\\\"a\\\":null},{\\\"n\\\":3,\\\"s\\\":\\\"s3\\\",\\\"o\\\":null,\\\"a\\\":null}]\"}";
        String actual = mapper.writeValueAsString(object);
        Assertions.assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    void shouldRespectJsonInclude() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        String expected = "{\"n\":0,\"s\":\"s0\",\"o\":{\"n\":1,\"s\":\"s1\"},\"a\":\"[{\\\"n\\\":2,\\\"s\\\":\\\"s2\\\"},{\\\"n\\\":3,\\\"s\\\":\\\"s3\\\"}]\"}";
        String actual = mapper.writeValueAsString(object);
        Assertions.assertThat(actual)
                .isEqualTo(expected);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JsonObject {
        private Number n;
        private String s;
        private JsonObject o;
        @JsonSerialize(using = JsonStringSerializer.class)
        private JsonObject[] a;
    }
}
