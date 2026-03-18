package org.navistack.framework.jackson;

import tools.jackson.core.JsonParser;
import tools.jackson.core.TreeNode;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.node.StringNode;

import java.util.LinkedHashMap;
import java.util.Map;

public class PolymorphicPropertyDeserializer<T> extends ValueDeserializer<T> {
    private static final String DEFAULT_PROPERTY = "type";

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
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        TreeNode treeNode = jsonParser.readValueAsTree();
        StringNode providerNode = (StringNode) treeNode.get(property);
        String provider = providerNode.asString();
        return jsonParser.readValueAs(subTypes.get(provider));
    }
}
