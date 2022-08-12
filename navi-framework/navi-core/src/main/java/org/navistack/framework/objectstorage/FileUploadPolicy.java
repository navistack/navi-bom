package org.navistack.framework.objectstorage;

import lombok.Data;
import org.navistack.framework.utils.Asserts;
import org.navistack.framework.utils.Objects;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Data
public class FileUploadPolicy {
    /**
     * Restrict size of file, size in bytes.
     * Set -1 to bypass the limit.
     */
    private long contentSizeLimit = -1;

    /**
     * Restrict content type of file content (MIME type).
     * set null to bypass the limit.
     */
    private Set<String> contentTypeLimit;

    public void setContentTypeLimit(Set<String> contentTypes) {
        Asserts.state(contentTypes, Objects::isNotNull, "contentTypes can not be null");
        this.contentTypeLimit = contentTypes;
    }

    public void setContentTypeLimit(String... contentTypes) {
        Asserts.state(contentTypes, Objects::isNotNull, "contentTypes can not be null");
        this.contentTypeLimit = new HashSet<>(Arrays.asList(contentTypes));
    }
}
