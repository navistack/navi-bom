package org.navistack.framework.objectstorage.namebuilders;

import org.navistack.framework.core.Builder;
import org.navistack.framework.utils.Asserts;
import org.navistack.framework.utils.Strings;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateBasedObjectNameBuilder implements Builder<String> {
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private final SecureRandom random = new SecureRandom();

    private final String objectName;

    public DateBasedObjectNameBuilder(String objectName) {
        Asserts.state(objectName, Strings::hasText, "objectName can not be empty");
        this.objectName = objectName;
    }

    @Override
    public String build() {
        LocalDateTime now = LocalDateTime.now();
        return String.format(
                "%s/%s%d_%s",
                DATE_FORMATTER.format(now),
                DATE_TIME_FORMATTER.format(now),
                random.nextInt(900) + 100,
                objectName
        );
    }
}
