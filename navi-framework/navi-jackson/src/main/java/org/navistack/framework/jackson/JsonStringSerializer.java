package org.navistack.framework.jackson;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

import java.io.StringWriter;

public class JsonStringSerializer<T> extends ValueSerializer<T> {
    @Override
    public void serialize(T value, JsonGenerator gen, SerializationContext context) throws JacksonException {
        StringWriter writer = new StringWriter();
        try (JsonGenerator generator = context.createGenerator(writer)) {
            context.writeValue(generator, value);
        }
        String json = writer.toString();
        gen.writeString(json);
    }
}
