package org.navistack.framework.objectstorage.namebuilders;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.navistack.framework.core.Builder;
import org.navistack.framework.utils.Asserts;
import org.navistack.framework.utils.InputOutputStreams;
import org.navistack.framework.utils.Objects;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class FileHashedObjectNameBuilder implements Builder<String> {
    private final File file;

    public FileHashedObjectNameBuilder(File file) {
        Asserts.state(file, Objects::isNotNull, "File can not be null");

        if (!file.exists()) {
            throw new IllegalArgumentException("File doesn't exist");
        }

        this.file = file;
    }

    public FileHashedObjectNameBuilder(String filename) {
        this(new File(filename));
    }

    @Override
    public String build() {
        byte[] bytes = calculateMd5Hash();
        char[] leadingBytesHex = Hex.encodeHex(bytes, 0, 2, true);
        char[] bytesHex = Hex.encodeHex(bytes, true);
        return new String(leadingBytesHex) + "/" + new String(bytesHex) + "/" + file.getName();
    }

    private byte[] calculateMd5Hash() {
        MessageDigest md5Digest = DigestUtils.getMd5Digest();
        try (InputStream fileInputStream = Files.newInputStream(file.toPath());
             DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, md5Digest)) {
            InputOutputStreams.drain(digestInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to build object name", e);
        }
        return md5Digest.digest();
    }
}
