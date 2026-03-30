package org.navistack.framework.jackson.web;

import org.navistack.framework.web.rest.RestOkResult;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

public class RestOkResultJsonSerializer extends StdSerializer<RestOkResult<?>> {
    public RestOkResultJsonSerializer() {
        super(RestOkResult.class);
    }

    @Override
    public void serialize(RestOkResult<?> ok,
                          JsonGenerator jsonGenerator,
                          SerializationContext context)
            throws JacksonException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeBooleanProperty("succeeded", ok.isSucceeded());
        jsonGenerator.writePOJOProperty("result", ok.getResult());
        jsonGenerator.writeEndObject();
    }
}
