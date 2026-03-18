package org.navistack.framework.jackson;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.core.ObjectReadContext;
import tools.jackson.databind.BeanProperty;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.introspect.AnnotatedMember;

@AllArgsConstructor
@NoArgsConstructor
public class JsonStringDeserializer<T> extends ValueDeserializer<T> {
    private JavaType type;

    @Override
    @SuppressWarnings("unchecked")
    public T deserialize(JsonParser parser, DeserializationContext context) throws JacksonException {
        String value = parser.getValueAsString();

        ObjectReadContext objectReadContext = parser.objectReadContext();
        return objectReadContext.readValue(objectReadContext.createParser(value), (Class<T>) type.getRawClass());
    }

    @Override
    public ValueDeserializer<?> createContextual(DeserializationContext deserializationContext,
                                                 BeanProperty beanProperty) {
        JavaType contextualType = deserializationContext.getContextualType();
        AnnotatedMember member = beanProperty.getMember();
        JavaType memberType = member.getType();
        JavaType type = contextualType != null ? contextualType : memberType;
        return new JsonStringDeserializer<>(type);
    }
}
