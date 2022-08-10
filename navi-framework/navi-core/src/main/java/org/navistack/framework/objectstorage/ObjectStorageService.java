package org.navistack.framework.objectstorage;

import org.navistack.framework.utils.Asserts;
import org.navistack.framework.utils.Objects;
import org.navistack.framework.utils.Strings;

import java.io.InputStream;

public interface ObjectStorageService {
    /**
     * Gets data from bucket of object.
     * Returned InputStream must be closed after use to release network resources.
     *
     * <p> Object name must have bucket name prefixed as part of its name in which the object is stored.
     * Bucket name and object name is seperated by ":".
     *
     * <p> For example, getObject("bucket:object") will return data from bucket named "bucket"
     * of object named "object".
     *
     * @param object name of object to get
     * @return InputStream to read object from
     */
    default InputStream getObject(String object) {
        String[] parts;
        Asserts.state(object, Objects::isNotNull, "object can not be null");
        Asserts.state((parts = object.split(":", 2)).length > 1, "object must contains bucket");
        return getObject(parts[0], parts[1]);
    }

    /**
     * Gets data from bucket of object.
     * Returned InputStream must be closed after use to release network resources.
     *
     * @param bucket which bucket to get object from
     * @param object name of object to get
     * @return InputStream to read object from
     */
    InputStream getObject(String bucket, String object);

    void putObject(String bucket, String object, InputStream inputStream);

    void uploadObject(String bucket, String object, String filename);

    default void removeObject(String object) {
        String[] parts;
        Asserts.state(object, Objects::isNotNull, "object can not be null");
        Asserts.state((parts = object.split(":", 2)).length > 1, "object must contains bucket");
        removeObject(parts[0], parts[1]);
    }

    void removeObject(String bucket, String object);
}