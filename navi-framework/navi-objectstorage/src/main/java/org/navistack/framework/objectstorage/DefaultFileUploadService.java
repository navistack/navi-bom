package org.navistack.framework.objectstorage;

import lombok.Getter;
import lombok.Setter;
import org.navistack.framework.objectstorage.namebuilders.FileHashedFileNameBuilder;
import org.navistack.framework.utils.Asserts;
import org.navistack.framework.utils.Strings;

import java.io.InputStream;
import java.nio.file.Path;

public class DefaultFileUploadService implements FileUploadService {
    @Getter
    private final ObjectStorageService objectStorageService;

    @Getter
    @Setter
    private FilenameBuilder filenameBuilder = new FileHashedFileNameBuilder();

    @Getter
    @Setter
    private FileUploadPolicyEnforcer uploadPolicyEnforcer = new DefaultFileUploadPolicyEnforcer();


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

        uploadPolicyEnforcer.enforce(filePath, contentType, policy);

        String managedFile = filenameBuilder.build(file, contentType);
        objectStorageService.uploadObject(bucket, managedFile, filePath.getFileName().toString());
        return new UploadedFileStat(bucket, managedFile)
                .setContentType(contentType)
                .setOriginalFilename(file)
                ;
    }

    @Override
    public void removeFile(String bucket, String file) {
        Asserts.state(bucket, Strings::hasText, "bucket can not be empty");
        Asserts.state(file, Strings::hasText, "file can not be empty");

        objectStorageService.removeObject(bucket, file);
    }
}
