package org.navistack.framework.objectstorage;

import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import lombok.Getter;
import lombok.Setter;
import org.navistack.framework.utils.Arrays;
import org.navistack.framework.utils.Strings;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.time.Duration;

public class MinioObjectStorageService implements ObjectStorageService {
    private final MinioClient minioClient;

    @Getter
    @Setter
    private String endpoint;

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
            throw new ObjectReadException(e);
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
            throw new ObjectManipulationException(e);
        }
    }

    public String getPermanentObjectUrl(String object) {
        String[] parts = Strings.split(object, ":");
        return getPermanentObjectUrl(Arrays.get(parts, 0), Arrays.get(parts, 1));
    }

    public String getPermanentObjectUrl(String bucket, String object) {
        if (endpoint == null) {
            return null;
        }
        return endpoint + "/" + bucket + "/" + object;
    }

    public String getPresignedObjectUrl(String object) {
        return getPresignedObjectUrl(object, Duration.ofDays(7));
    }

    public String getPresignedObjectUrl(String object, Duration expiration) {
        return getPresignedObjectUrl(object, expiration, "GET");
    }

    public String getPresignedObjectUrl(String object, Duration expiration, String method) {
        String[] parts = Strings.split(object, ":");
        return getPresignedObjectUrl(Arrays.get(parts, 0), Arrays.get(parts, 1), expiration, method);
    }

    public String getPresignedObjectUrl(String bucket, String object, Duration expiration, String method) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .bucket(bucket)
                            .object(object)
                            .expiry((int) expiration.getSeconds())
                            .method(Method.valueOf(method))
                            .build()
            );
        } catch (IOException | MinioException | GeneralSecurityException e) {
            throw new ObjectManipulationException(e);
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
            throw new BucketManipulationException(e);
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
            throw new BucketManipulationException(e);
        }
    }

    public void removeBucket(String bucket) {
        try {
            minioClient.removeBucket(
                    RemoveBucketArgs.builder()
                            .bucket(bucket)
                            .build()
            );
        } catch (IOException | MinioException | GeneralSecurityException e) {
            throw new BucketManipulationException(e);
        }
    }

    private void makeBucketIfNecessary(String bucket) {
        if (makeBucketIfNotExisted && bucketExists(bucket)) {
            makeBucket(bucket);
        }
    }
}
