package org.navistack.framework.objectstorage.namebuilders;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.navistack.framework.objectstorage.FilenameBuilder;
import org.navistack.framework.utils.Asserts;
import org.navistack.framework.utils.InputOutputStreams;
import org.navistack.framework.utils.Strings;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class FileHashedFileNameBuilder implements FilenameBuilder {
    @Override
    public String build(String filename, String contentType) {
        Asserts.state(filename, Strings::hasText, "filename can not be empty");
        return build(new File(filename), contentType);
    }

    @Override
    public String build(Path filepath, String contentType) {
        Asserts.notNull(filepath, "filepath can not be null");
        return build(filepath.toFile(), contentType);
    }

    @Override
    public String build(File file, String contentType) {
        Asserts.notNull(file, "file can not be null");
        byte[] bytes = calculateMd5Hash(file);
        char[] leadingBytesHex = Hex.encodeHex(bytes, 0, 1, true);
        char[] bytesHex = Hex.encodeHex(bytes, true);
        return new String(leadingBytesHex) + "/" + new String(bytesHex) + "/" + file.getName();
    }

    private static byte[] calculateMd5Hash(File file) {
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
