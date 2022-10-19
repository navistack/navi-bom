package org.navistack.framework.jackson.web;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.navistack.framework.web.rest.RestResult;

import java.io.IOException;
import java.util.Map;

public class ParameterizedErrorJsonSerializer extends JsonSerializer<RestResult.ParameterizedError> {
    @Override
    public void serialize(RestResult.ParameterizedError error, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("code", error.getError());
        jsonGenerator.writeStringField("message", error.getMessage());
        for (Map.Entry<String, Object> entry : error.getParameters().entrySet()) {
            jsonGenerator.writeObjectField(entry.getKey(), entry.getValue());
        }
        jsonGenerator.writeEndObject();
    }
}
