package org.navistack.framework.jackson.web;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.navistack.framework.web.rest.RestOkResult;

import java.io.IOException;

public class RestOkResultJsonSerializer extends JsonSerializer<RestOkResult<?>> {
    @Override
    public void serialize(RestOkResult ok,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeBooleanField("succeeded", ok.isSucceeded());
        jsonGenerator.writeObjectField("result", ok.getResult());
        jsonGenerator.writeEndObject();
    }
}
