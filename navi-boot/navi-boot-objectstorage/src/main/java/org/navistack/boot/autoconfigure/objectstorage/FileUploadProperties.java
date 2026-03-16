package org.navistack.boot.autoconfigure.objectstorage;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

@ConfigurationProperties(prefix = FileUploadProperties.PROPERTIES_PREFIX)
@Data
public class FileUploadProperties {
    public static final String PROPERTIES_PREFIX = "navi.file-upload";

    private UploadPolicy uploadPolicy;

    @Data
    public static class UploadPolicy {
        private long contentSizeLimit = -1;
        private Set<String> contentTypeLimit;
    }
}
