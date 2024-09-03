package org.navistack.framework.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
@NoArgsConstructor
public class JsonStringDeserializer<T> extends JsonDeserializer<T> implements ContextualDeserializer {
    private JavaType type;

    @Override
    @SuppressWarnings("unchecked")
    public T deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
        String value = parser.getValueAsString();

        ObjectCodec codec = parser.getCodec();
        JsonFactory factory = codec.getFactory();

        return codec.readValue(factory.createParser(value), (Class<T>) type.getRawClass());
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext,
                                                BeanProperty beanProperty) throws JsonMappingException {
        JavaType contextualType = deserializationContext.getContextualType();
        AnnotatedMember member = beanProperty.getMember();
        JavaType memberType = member.getType();
        JavaType type = contextualType != null ? contextualType : memberType;
        return new JsonStringDeserializer<>(type);
    }
}
