package org.navistack.framework.jackson.web;

import org.navistack.framework.web.rest.RestErrResult;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

import java.util.Map;

public class RestErrResultJsonSerializer extends ValueSerializer<RestErrResult> {
    @Override
    public void serialize(RestErrResult err,
                          JsonGenerator jsonGenerator,
                          SerializationContext context)
            throws JacksonException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeBooleanProperty("succeeded", err.isSucceeded());
        jsonGenerator.writeNumberProperty("code", err.getError());
        jsonGenerator.writeStringProperty("message", err.getMessage());
        for (Map.Entry<String, Object> entry : err.getParameters().entrySet()) {
            jsonGenerator.writePOJOProperty(entry.getKey(), entry.getValue());
        }
        jsonGenerator.writeEndObject();
    }
}
