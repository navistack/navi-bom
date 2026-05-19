package org.navistack.framework.jackson;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JsonStringDeserializerTest {
    @Test
    void shouldDeserializeJsonStringAsObjectOfTargetTypeWhenJsonStringContainsArray() {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"n\":0,\"s\":\"s0\",\"o\":{\"n\":1,\"s\":\"s1\"},\"a\":\"[{\\\"n\\\":2,\\\"s\\\":\\\"s2\\\"},{\\\"n\\\":3,\\\"s\\\":\\\"s3\\\"}]\"}";
        JsonObject actual = mapper.readValue(json, JsonObject.class);
        JsonObject expected = JsonObject.builder()
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
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void shouldDeserializeNullAsNullWhenJsonValueIsNull() {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"n\":1,\"s\":\"s1\",\"o\":null,\"a\":null}";
        JsonObject actual = mapper.readValue(json, JsonObject.class);
        assertThat(actual.getA()).isNull();
    }

    @Test
    void shouldDeserializeEmptyArrayWhenJsonStringIsEmptyArray() {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"n\":1,\"s\":\"s1\",\"o\":null,\"a\":\"[]\"}";
        JsonObject actual = mapper.readValue(json, JsonObject.class);
        assertThat(actual.getA()).isNotNull().isEmpty();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JsonObject {
        private Number n;
        private String s;
        private JsonObject o;
        @JsonDeserialize(using = JsonStringDeserializer.class)
        private JsonObject[] a;
    }
}
