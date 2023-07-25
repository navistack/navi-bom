package org.navistack.framework.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class JsonStringSerializer<T> extends JsonSerializer<T> {
    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        ObjectCodec codec = gen.getCodec();
        JsonFactory factory = codec.getFactory();
        Writer writer = new StringWriter();
        JsonGenerator generator = factory.createGenerator(writer);
        codec.writeValue(generator, value);
        String json = writer.toString();
        gen.writeString(json);
    }
}
