package org.navistack.framework.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractErrCategory implements ErrCategory {
    private final static String FALLBACK_MESSAGE = "Unknown error %d";

    private final Map<Integer, String> messages;

    public AbstractErrCategory() {
        messages = new ConcurrentHashMap<>();
    }

    public AbstractErrCategory(Map<Integer, String> messages) {
        this();
        putMessages(messages);
    }

    @Override
    public String message(int value) {
        String message = messages.get(value);
        return message != null
                ? message
                : String.format(FALLBACK_MESSAGE, value);
    }

    public void putMessage(int value, String message) {
        messages.put(value, message);
    }

    public void putMessages(Map<Integer, String> messages) {
        this.messages.putAll(messages);
    }
}
