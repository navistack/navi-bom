package org.navistack.framework.objectstorage;

import lombok.Getter;
import org.navistack.framework.utils.Asserts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

public class DefaultFileUploadPolicyEnforcer implements FileUploadPolicyEnforcer {
    @Getter
    private FileUploadPolicy defaultUploadPolicy;

    public DefaultFileUploadPolicyEnforcer() {
        this.defaultUploadPolicy = new FileUploadPolicy();
    }

    public DefaultFileUploadPolicyEnforcer(FileUploadPolicy defaultUploadPolicy) {
        this.defaultUploadPolicy = defaultUploadPolicy;
    }

    public void setDefaultUploadPolicy(FileUploadPolicy defaultUploadPolicy) {
        Asserts.notNull(defaultUploadPolicy, "defaultUploadPolicy can not be null");
        this.defaultUploadPolicy = defaultUploadPolicy;
    }

    @Override
    public void enforce(Path filePath, String contentType, FileUploadPolicy policy) throws FileUploadPolicyViolationException {
        if (policy == null) {
            if (defaultUploadPolicy == null) {
                return;
            }
            policy = defaultUploadPolicy;
        }

        long sizeLimit = policy.getContentSizeLimit();
        if (sizeLimit >= 0) {
            try {
                long fileSize = Files.size(filePath);
                if (fileSize > sizeLimit) {
                    throw new FileSizeLimitExceededException("File too large " + fileSize);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Set<String> typeLimit = policy.getContentTypeLimit();
        if (typeLimit != null) {
            try {
                String actualContentType = Files.probeContentType(filePath);
                if (actualContentType != null) {
                    contentType = actualContentType;
                }
            } catch (IOException e) {
                throw new FileSizeLimitExceededException("Error occurred while probing filetype", e);
            }

            if (!typeLimit.contains(contentType)) {
                throw new InvalidContentTypeException("filetype " + contentType + " not allowed");
            }
        }
    }
}
