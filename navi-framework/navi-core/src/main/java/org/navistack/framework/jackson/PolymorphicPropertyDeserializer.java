package org.navistack.framework.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class PolymorphicPropertyDeserializer<T> extends JsonDeserializer<T> {
    private final static String DEFAULT_PROPERTY = "type";

    private final Map<String, Class<? extends T>> subTypes = new LinkedHashMap<>();

    private final String property;

    public PolymorphicPropertyDeserializer() {
        this(DEFAULT_PROPERTY);
    }

    public PolymorphicPropertyDeserializer(Map<String, Class<? extends T>> subTypes) {
        this(DEFAULT_PROPERTY, subTypes);
    }

    protected PolymorphicPropertyDeserializer(String property) {
        this.property = property;
    }

    protected PolymorphicPropertyDeserializer(String property, Map<String, Class<? extends T>> subTypes) {
        this.property = property;
        this.subTypes.putAll(subTypes);
    }

    public Map<String, Class<? extends T>> getSubTypes() {
        return subTypes;
    }

    public void registerSubTypes(Map<String, Class<? extends T>> subTypes) {
        this.subTypes.putAll(subTypes);
    }

    public void registerSubType(String name, Class<? extends T> clazz) {
        this.subTypes.put(name, clazz);
    }

    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        TreeNode treeNode = jsonParser.readValueAsTree();
        TextNode providerNode = (TextNode) treeNode.get(property);
        String provider = providerNode.asText();
        return jsonParser.readValueAs(subTypes.get(provider));
    }
}
