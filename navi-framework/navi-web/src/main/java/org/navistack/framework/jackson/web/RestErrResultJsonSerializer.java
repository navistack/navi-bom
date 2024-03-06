package org.navistack.framework.jackson.web;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.navistack.framework.web.rest.RestErrResult;

import java.io.IOException;
import java.util.Map;

public class RestErrResultJsonSerializer extends JsonSerializer<RestErrResult> {
    @Override
    public void serialize(RestErrResult err,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeBooleanField("succeeded", err.isSucceeded());
        jsonGenerator.writeNumberField("code", err.getError());
        jsonGenerator.writeStringField("message", err.getMessage());
        for (Map.Entry<String, Object> entry : err.getParameters().entrySet()) {
            jsonGenerator.writeObjectField(entry.getKey(), entry.getValue());
        }
        jsonGenerator.writeEndObject();
    }
}
