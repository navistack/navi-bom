package org.navistack.framework.objectstorage;

import io.minio.*;
import io.minio.errors.MinioException;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;

public class MinioObjectStorageService implements ObjectStorageService {
    private final MinioClient minioClient;

    @Getter
    @Setter
    private boolean makeBucketIfNotExisted = false;

    public MinioObjectStorageService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public InputStream getObject(String bucket, String object) {
        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucket)
                            .object(object)
                            .build()
            );
        } catch (IOException | MinioException | GeneralSecurityException e) {
            throw new ObjectWriteException(e);
        }
    }

    @Override
    public void uploadObject(String bucket, String object, String filename) {
        try {
            makeBucketIfNecessary(bucket);
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket(bucket)
                            .object(object)
                            .filename(filename)
                            .build()
            );
        } catch (IOException | MinioException | GeneralSecurityException e) {
            throw new ObjectWriteException(e);
        }
    }

    @Override
    public void putObject(String bucket, String object, InputStream inputStream) {
        try {
            makeBucketIfNecessary(bucket);
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(object)
                            .stream(inputStream, -1, 10485760)
                            .build()
            );
        } catch (IOException | MinioException | GeneralSecurityException e) {
            throw new ObjectWriteException(e);
        }
    }

    @Override
    public void removeObject(String bucket, String object) {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucket)
                            .object(object)
                            .build()
            );
        } catch (IOException | MinioException | GeneralSecurityException e) {
            throw new ObjectWriteException(e);
        }
    }

    public boolean bucketExists(String bucket) {
        try {
            return minioClient.bucketExists(
                    BucketExistsArgs.builder()
                            .bucket(bucket)
                            .build()
            );
        } catch (IOException | MinioException | GeneralSecurityException e) {
            throw new ObjectWriteException(e);
        }
    }

    public void makeBucket(String bucket) {
        try {
            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(bucket)
                            .build()
            );
        } catch (IOException | MinioException | GeneralSecurityException e) {
            throw new ObjectWriteException(e);
        }
    }

    private void makeBucketIfNecessary(String bucket) {
        if (makeBucketIfNotExisted && bucketExists(bucket)) {
            makeBucket(bucket);
        }
    }
}
