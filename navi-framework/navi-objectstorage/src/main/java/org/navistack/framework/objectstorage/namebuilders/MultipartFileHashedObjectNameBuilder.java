package org.navistack.framework.objectstorage.namebuilders;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.navistack.framework.core.Builder;
import org.navistack.framework.utils.Asserts;
import org.navistack.framework.utils.InputOutputStreams;
import org.navistack.framework.utils.Objects;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class MultipartFileHashedObjectNameBuilder implements Builder<String> {
    private final MultipartFile file;

    public MultipartFileHashedObjectNameBuilder(MultipartFile file) {
        Asserts.state(file, Objects::isNotNull, "File can not be null");

        this.file = file;
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
        try (InputStream fileInputStream = file.getInputStream();
             DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, md5Digest)) {
            InputOutputStreams.drain(digestInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to build object name", e);
        }
        return md5Digest.digest();
    }
}
