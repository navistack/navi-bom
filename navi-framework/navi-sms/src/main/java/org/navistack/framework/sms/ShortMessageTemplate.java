package org.navistack.framework.sms;

public interface ShortMessageTemplate {
    /**
     * Message template code
     */
    String getCode();

    /**
     * Plain Message content or message template
     */
    String getMessage();
}
