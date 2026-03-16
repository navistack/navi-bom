package org.navistack.boot.autoconfigure.objectstorage;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = FilesystemProperties.PROPERTIES_PREFIX)
@Data
public class FilesystemProperties {
    public static final String PROPERTIES_PREFIX = "navi.object-storage.filesystem";

    private String dataDir;
}
