package org.navistack.framework.sms;

import java.util.Collections;
import java.util.Map;

public interface ShortMessage {
    /**
     * Phone number to which the message will be sent
     */
    String getPhoneNumber();

    /**
     * Entity that sent the message
     */
    String getSignature();

    /**
     * Plain Message content, message template or template id
     */
    String getMessage();

    /**
     * Arguments for use by message template
     */
    default Map<String, Object> getArguments() {
        return Collections.emptyMap();
    }

    /**
     * Additional attributes for use by specified implementation.
     */
    default Map<String, Object> getAttributes() {
        return Collections.emptyMap();
    }
}
