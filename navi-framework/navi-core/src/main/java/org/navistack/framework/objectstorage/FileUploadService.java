package org.navistack.framework.objectstorage;

import org.navistack.framework.utils.Asserts;
import org.navistack.framework.utils.Strings;

import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public interface FileUploadService {
    /**
     * Gets data from bucket of file.
     * Returned InputStream must be closed after use to release network resources.
     *
     * <p> Object name must have bucket name prefixed as part of its name in which the file is stored.
     * Bucket name and file name is seperated by ":".
     *
     * <p> For example, getObject("bucket:file") will return data from bucket named "bucket"
     * of file named "file".
     *
     * @param file name of file to get, having bucket name prefixed
     * @return InputStream to read file from
     */
    default InputStream getFile(String file) {
        String[] parts = Strings.split(file, ":");
        Asserts.state(parts.length > 1, "file must contain bucket");
        return getFile(parts[0], parts[1]);
    }

    /**
     * Gets data from bucket of file.
     * Returned InputStream must be closed after use to release network resources.
     *
     * @param bucket which bucket to get file from
     * @param file   name of file to get
     * @return InputStream to read file from
     */
    InputStream getFile(String bucket, String file);

    /**
     * Get file stat of uploaded file
     *
     * @param file   name of file to get, having bucket name prefixed
     * @return file stat
     */
    default UploadedFileStat statFile(String file) {
        String[] parts = Strings.split(file, ":");
        Asserts.state(parts.length > 1, "file must contain bucket");
        return statFile(parts[0], parts[1]);
    }

    /**
     * Get file stat of uploaded file
     *
     * @param bucket which bucket to get file from
     * @param file   name of file to get
     * @return file stat
     */
    UploadedFileStat statFile(String bucket, String file);

    /**
     * Upload file, with default upload policy
     *
     * @param bucket      bucket in where file is stored
     * @param file        filename
     * @param inputStream InputStream to read content
     * @param contentType Content type of file
     */
    UploadedFileStat upload(String bucket, String file, InputStream inputStream, String contentType);


    /**
     * Upload local file.
     *
     * @param bucket      bucket in where file is stored
     * @param file        filename
     * @param filePath    path to local file
     * @param contentType Content type of file to use on failing probing
     * @param policy      upload policy
     */
    UploadedFileStat uploadFile(String bucket, String file, Path filePath, String contentType, FileUploadPolicy policy);

    /**
     * Upload local file
     *
     * @param bucket      bucket in where file is stored
     * @param file        filename
     * @param filename    local file name
     * @param contentType Content type of file on failing probing
     * @param policy      upload policy
     */
    default UploadedFileStat uploadFile(String bucket, String file, String filename, String contentType, FileUploadPolicy policy) {
        return uploadFile(bucket, file, FileSystems.getDefault().getPath(filename), contentType, policy);
    }

    /**
     * Upload local file, with default upload policy
     *
     * @param bucket      bucket in where file is stored
     * @param file        filename
     * @param filename    local file name
     * @param contentType Content type of file on failing probing
     */
    default UploadedFileStat uploadFile(String bucket, String file, String filename, String contentType) {
        return uploadFile(bucket, file, filename, contentType, null);
    }

    /**
     * Remove file
     *
     * @param file name of file to be deleted, having bucket name prefixed
     */
    default void removeFile(String file) {
        String[] parts = Strings.split(file, ":");
        Asserts.state(parts.length > 1, "file must contain bucket");
        removeFile(parts[0], parts[1]);
    }

    /**
     * Remove file
     *
     * @param bucket bucket in where file is stored
     * @param file   name of file to be deleted
     */
    void removeFile(String bucket, String file);
}
