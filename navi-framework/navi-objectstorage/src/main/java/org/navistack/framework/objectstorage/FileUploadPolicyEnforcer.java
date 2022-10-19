package org.navistack.framework.objectstorage;

import java.nio.file.Path;

public interface FileUploadPolicyEnforcer {
    void enforce(Path filePath, String contentType, FileUploadPolicy policy) throws FileUploadPolicyViolationException;
}
