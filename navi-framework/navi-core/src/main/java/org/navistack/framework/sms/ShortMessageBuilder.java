package org.navistack.framework.sms;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(fluent = true)
@NonNull
public class ShortMessageBuilder {
    private String phoneNumber;
    private String signature;
    private String message;
    private final Map<String, Object> arguments = new HashMap<>();
    private final Map<String, Object> attributes = new HashMap<>();

    public ShortMessageBuilder argument(String key, Object value) {
        if (key == null) {
            throw new NullPointerException("key must not be null");
        }
        if (value == null) {
            throw new NullPointerException("value must not be null");
        }
        arguments.put(key, value);
        return this;
    }

    public ShortMessageBuilder arguments(Map<? extends String, ?> arguments) {
        if (arguments == null || arguments.isEmpty()) {
            return this;
        }
        this.arguments.putAll(arguments);
        return this;
    }

    public ShortMessageBuilder attribute(String key, Object value) {
        if (key == null) {
            throw new NullPointerException("key must not be null");
        }
        if (value == null) {
            throw new NullPointerException("value must not be null");
        }
        attributes.put(key, value);
        return this;
    }

    public ShortMessageBuilder attributes(Map<? extends String, ?> attributes) {
        if (attributes == null || attributes.isEmpty()) {
            return this;
        }
        this.attributes.putAll(attributes);
        return this;
    }

    public SimpleShortMessage build() {
        SimpleShortMessage shortMessage = new SimpleShortMessage();
        shortMessage.setPhoneNumber(phoneNumber);
        shortMessage.setSignature(signature);
        shortMessage.setMessage(message);
        shortMessage.setArguments(arguments);
        shortMessage.setAttributes(attributes);
        return shortMessage;
    }
}
