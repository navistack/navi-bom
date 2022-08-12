package org.navistack.framework.objectstorage;

import lombok.Getter;
import lombok.Setter;
import org.navistack.framework.objectstorage.namebuilders.FileHashedFileNameBuilder;
import org.navistack.framework.utils.Asserts;
import org.navistack.framework.utils.Strings;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

public class DefaultFileUploadService implements FileUploadService {
    @Getter
    private final ObjectStorageService objectStorageService;

    @Getter
    @Setter
    private FilenameBuilder filenameBuilder = new FileHashedFileNameBuilder();

    @Getter
    private FileUploadPolicy defaultUploadPolicy = new FileUploadPolicy();


    public DefaultFileUploadService(ObjectStorageService objectStorageService) {
        this.objectStorageService = objectStorageService;
    }

    @Override
    public UploadedFileStat statFile(String bucket, String file) {
        throw new UnsupportedOperationException();
    }

    @Override
    public InputStream getFile(String bucket, String file) {
        Asserts.state(bucket, Strings::hasText, "bucket can not be empty");
        Asserts.state(file, Strings::hasText, "file can not be empty");

        return objectStorageService.getObject(bucket, file);
    }

    public void setDefaultUploadPolicy(FileUploadPolicy defaultUploadPolicy) {
        Asserts.notNull(defaultUploadPolicy, "defaultUploadPolicy can not be null");
        this.defaultUploadPolicy = defaultUploadPolicy;
    }

    @Override
    public UploadedFileStat upload(String bucket, String file, InputStream inputStream, String contentType) {
        Asserts.state(bucket, Strings::hasText, "bucket can not be empty");
        Asserts.state(file, Strings::hasText, "file can not be empty");
        Asserts.notNull(inputStream, "inputStream can not be null");

        String managedFile = filenameBuilder.build(file, contentType);
        objectStorageService.putObject(bucket, managedFile, inputStream);
        return new UploadedFileStat(bucket, managedFile)
                .setContentType(contentType)
                .setOriginalFilename(file)
                ;
    }

    @Override
    public UploadedFileStat uploadFile(String bucket, String file, Path filePath, String contentType, FileUploadPolicy policy) {
        Asserts.state(bucket, Strings::hasText, "bucket can not be empty");
        Asserts.state(file, Strings::hasText, "file can not be empty");
        Asserts.notNull(filePath, "filePath can not be null");

        ensureUploadPolicy(filePath, contentType, policy);

        String managedFile = filenameBuilder.build(file, contentType);
        objectStorageService.uploadObject(bucket, managedFile, filePath.getFileName().toString());
        return new UploadedFileStat(bucket, managedFile)
                .setContentType(contentType)
                .setOriginalFilename(file)
                ;
    }

    private void ensureUploadPolicy(Path filePath, String contentType, FileUploadPolicy policy) {
        long sizeLimit = policy.getContentSizeLimit();
        if (sizeLimit >= 0) {
            try {
                long fileSize = Files.size(filePath);
                if (fileSize > sizeLimit) {
                    throw new FileUploadPolicyViolationException("File too large " + fileSize);
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
                throw new FileUploadPolicyViolationException("Error occurred while probing filetype", e);
            }

            if (typeLimit.contains(contentType)) {
                throw new FileUploadPolicyViolationException("filetype " + contentType + " not allowed");
            }
        }
    }

    @Override
    public void removeFile(String bucket, String file) {
        Asserts.state(bucket, Strings::hasText, "bucket can not be empty");
        Asserts.state(file, Strings::hasText, "file can not be empty");

        objectStorageService.removeObject(bucket, file);
    }
}
