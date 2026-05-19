package org.navistack.framework.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.json.JsonMapper;

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
    void shouldSerializeAsJsonStringWhenUsingDefaultMapper() {
        JsonMapper mapper = JsonMapper.shared();

        String expected = "{\"n\":0,\"s\":\"s0\",\"o\":{\"n\":1,\"s\":\"s1\",\"o\":null,\"a\":null},\"a\":\"[{\\\"n\\\":2,\\\"s\\\":\\\"s2\\\",\\\"o\\\":null,\\\"a\\\":null},{\\\"n\\\":3,\\\"s\\\":\\\"s3\\\",\\\"o\\\":null,\\\"a\\\":null}]\"}";
        String actual = mapper.writeValueAsString(object);
        Assertions.assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    void shouldRespectJsonIncludeWhenContentAndValueInclusionIsNonNull() {
        JsonMapper mapper = JsonMapper.builder()
                .changeDefaultPropertyInclusion(incl -> incl.withValueInclusion(JsonInclude.Include.NON_NULL))
                .changeDefaultPropertyInclusion(incl -> incl.withContentInclusion(JsonInclude.Include.NON_NULL))
                .build();

        String expected = "{\"n\":0,\"s\":\"s0\",\"o\":{\"n\":1,\"s\":\"s1\"},\"a\":\"[{\\\"n\\\":2,\\\"s\\\":\\\"s2\\\"},{\\\"n\\\":3,\\\"s\\\":\\\"s3\\\"}]\"}";
        String actual = mapper.writeValueAsString(object);
        Assertions.assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    void shouldSerializeNullArrayAsNullWhenArrayIsNull() {
        JsonMapper mapper = JsonMapper.shared();
        JsonObject objectWithNullArray = JsonObject.builder()
                .n(10)
                .s("s10")
                .a(null)
                .build();

        String expected = "{\"n\":10,\"s\":\"s10\",\"o\":null,\"a\":null}";
        String actual = mapper.writeValueAsString(objectWithNullArray);
        Assertions.assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    void shouldSerializeEmptyArrayAsEmptyJsonArrayStringWhenArrayIsEmpty() {
        JsonMapper mapper = JsonMapper.shared();
        JsonObject objectWithEmptyArray = JsonObject.builder()
                .n(20)
                .s("s20")
                .a(new JsonObject[0])
                .build();

        String expected = "{\"n\":20,\"s\":\"s20\",\"o\":null,\"a\":\"[]\"}";
        String actual = mapper.writeValueAsString(objectWithEmptyArray);
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
