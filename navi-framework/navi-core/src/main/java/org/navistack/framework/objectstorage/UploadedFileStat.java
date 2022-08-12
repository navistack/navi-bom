package org.navistack.framework.objectstorage;

import lombok.Data;
import lombok.experimental.Accessors;
import org.navistack.framework.utils.Asserts;
import org.navistack.framework.utils.Strings;

@Data
@Accessors(chain = true)
public class UploadedFileStat {
    /**
     * file hash, generally generated using MD5 algorithm
     */
    private String etag;

    /**
     * The bucket where the file is stored
     */
    private String bucket;

    /**
     * file name referenced to file stored in bucket
     */
    private String fileName;

    /**
     * Original file name set by user
     */
    private String originalFilename;

    /**
     * Content type of the file
     */
    private String contentType;

    /**
     * public accessible uri to this file
     */
    private String publicUri;

    public UploadedFileStat(String bucket, String fileName) {
        Asserts.state(bucket, Strings::hasText, "bucket can not be empty");
        Asserts.state(bucket, Strings::hasText, "fileName can not be empty");

        this.bucket = bucket;
        this.fileName = fileName;
    }

    /**
     * a unique name combines bucket name and file name, separated with a colon ":"
     */
    public String getUniqueObjectName() {
        return bucket + ":" + fileName;
    }
}
