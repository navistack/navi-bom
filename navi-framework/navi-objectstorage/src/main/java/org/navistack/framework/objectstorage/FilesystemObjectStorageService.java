package org.navistack.framework.objectstorage;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.navistack.framework.utils.Asserts;
import org.navistack.framework.utils.InputOutputStreams;
import org.navistack.framework.utils.Objects;
import org.navistack.framework.utils.Strings;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesystemObjectStorageService implements ObjectStorageService {
    private final File dataDir;

    @Getter
    @Setter
    @NonNull
    private PublicRootUriSupplier publicRootUriSupplier = new HttpServletRequestPublicRootUriSupplier();

    public FilesystemObjectStorageService(File dataDir) {
        Asserts.state(dataDir.isDirectory() && dataDir.exists() && dataDir.canWrite(), "dataDir must be existing, writable directory");
        this.dataDir = dataDir;
    }

    public FilesystemObjectStorageService(String dataDir) {
        this(new File(dataDir));
    }

    @Override
    public InputStream getObject(String bucket, String object) {
        Asserts.state(bucket, Strings::hasText, "bucket can not be empty");
        Asserts.state(object, Strings::hasText, "object can not be empty");

        File file = new File(getBucketDir(bucket), object);
        if (!file.exists()) {
            throw new ObjectReadException("No such object " + object);
        }
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new ObjectReadException("Failed to open file for object + " + object, e);
        }
    }

    @Override
    public String getObjectUri(String bucket, String object) {
        Asserts.state(bucket, Strings::hasText, "bucket can not be empty");
        Asserts.state(object, Strings::hasText, "object can not be empty");

        URI publicRoot = publicRootUriSupplier.get();
        if (publicRoot == null) {
            return bucket + "/" + object;
        } else {
            return publicRoot + bucket + "/" + object;
        }
    }

    @Override
    public void putObject(String bucket, String object, InputStream inputStream) {
        Asserts.state(bucket, Strings::hasText, "bucket can not be empty");
        Asserts.state(object, Strings::hasText, "object can not be empty");
        Asserts.state(inputStream, Objects::isNotNull, "inputStream can not be empty");

        File file = new File(getBucketDir(bucket), object);
        File parentFile = file.getParentFile();
        if (parentFile != null && parentFile.mkdirs()) {
            throw new ObjectWriteException("Failed To create directory for object " + object);
        }
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            InputOutputStreams.transfer(inputStream, outputStream);
        } catch (IOException e) {
            throw new ObjectWriteException(e);
        }
    }

    @Override
    public void uploadObject(String bucket, String object, String filename) {
        Asserts.state(filename, Strings::hasText, "filename can not be empty");

        try (InputStream inputStream = Files.newInputStream(Paths.get(filename))) {
            putObject(bucket, object, inputStream);
        } catch (IOException e) {
            throw new ObjectWriteException(e);
        }
    }

    @Override
    public void removeObject(String bucket, String object) {
        Asserts.state(bucket, Strings::hasText, "bucket can not be empty");
        Asserts.state(object, Strings::hasText, "object can not be empty");

        File file = new File(getBucketDir(bucket), object);
        if (!file.exists()) {
            throw new ObjectManipulationException("No such object " + object);
        }
        if (!file.delete()) {
            throw new ObjectManipulationException("Failed to delete object" + object);
        }
    }

    private File getBucketDir(String bucket) {
        File bucketDir = new File(dataDir, bucket);
        if (bucketDir.exists()) {
            if (!bucketDir.isDirectory()) {
                throw new ObjectWriteException("Bucket " + bucket + " exists as a file");

            }
        } else if (!bucketDir.mkdir()) {
            throw new ObjectWriteException("Failed To create directory for bucket " + bucket);
        }
        return bucketDir;
    }
}
