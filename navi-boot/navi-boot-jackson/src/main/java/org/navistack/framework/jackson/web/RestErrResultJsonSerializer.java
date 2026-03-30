package org.navistack.framework.jackson.web;

import org.navistack.framework.web.rest.RestErrResult;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

import java.util.Map;

public class RestErrResultJsonSerializer extends StdSerializer<RestErrResult> {
    public RestErrResultJsonSerializer() {
        super(RestErrResult.class);
    }

    @Override
    public void serialize(RestErrResult err,
                          JsonGenerator jsonGenerator,
                          SerializationContext context)
            throws JacksonException {
        jsonGenerator.writeStartObject();

        boolean succeeded = err.isSucceeded();
        jsonGenerator.writeBooleanProperty("succeeded", succeeded);

        int error = err.getError();
        jsonGenerator.writeNumberProperty("error", error);

        String message = err.getMessage();
        jsonGenerator.writeStringProperty("message", message);

        String endpoint = err.getEndpoint();
        if (endpoint != null) {
            jsonGenerator.writeStringProperty("endpoint", endpoint);
        }

        Map<String, Object> parameters = err.getParameters();
        if (parameters != null && !parameters.isEmpty()) {
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                jsonGenerator.writePOJOProperty(entry.getKey(), entry.getValue());
            }
        }

        jsonGenerator.writeEndObject();
    }
}
