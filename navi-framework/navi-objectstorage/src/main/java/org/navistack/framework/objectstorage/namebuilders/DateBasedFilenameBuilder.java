package org.navistack.framework.objectstorage.namebuilders;

import lombok.Getter;
import lombok.Setter;
import org.navistack.framework.objectstorage.FilenameBuilder;
import org.navistack.framework.random.RandomGenerator;
import org.navistack.framework.random.SecureRandomGenerator;
import org.navistack.framework.utils.Asserts;
import org.navistack.framework.utils.Strings;

import java.io.File;
import java.nio.file.Path;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateBasedFilenameBuilder implements FilenameBuilder {
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Getter
    @Setter
    private RandomGenerator random = new SecureRandomGenerator();

    @Getter
    @Setter
    private Clock clock = Clock.system(ZoneId.systemDefault());

    @Override
    public String build(String filename, String contentType) {
        Asserts.state(filename, Strings::hasText, "filename can not be empty");

        LocalDateTime now = LocalDateTime.now(clock);
        return String.format(
                "%s/%s%d_%s",
                DATE_FORMATTER.format(now),
                DATE_TIME_FORMATTER.format(now),
                random.nextInt(900) + 100,
                filename
        );
    }

    @Override
    public String build(Path filepath, String contentType) {
        Asserts.notNull(filepath, "filepath can not be null");
        return build(filepath.getFileName().toString(), contentType);
    }

    @Override
    public String build(File file, String contentType) {
        Asserts.notNull(file, "file can not be null");
        return build(file.getName(), contentType);
    }
}
