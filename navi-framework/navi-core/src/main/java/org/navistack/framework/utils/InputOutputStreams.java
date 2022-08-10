package org.navistack.framework.utils;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@UtilityClass
public class InputOutputStreams {
    public final int BUFFER_SIZE = 8192;

    public long transfer(InputStream in, OutputStream out) throws IOException {
        Asserts.state(in, Objects::isNotNull, "in can not be null");
        Asserts.state(out, Objects::isNotNull, "out can not be null");

        long offset = 0;
        byte[] buffer = new byte[BUFFER_SIZE];
        int read;
        while ((read = in.read(buffer, 0, BUFFER_SIZE)) >= 0) {
            out.write(buffer, 0, read);
            offset += read;
        }
        out.flush();
        return offset;
    }

    public long drain(InputStream in) throws IOException {
        Asserts.state(in, Objects::isNotNull, "in can not be null");

        int offset = 0;
        byte[] buffer = new byte[BUFFER_SIZE];
        int read;
        while ((read = in.read(buffer, 0, BUFFER_SIZE)) >= 0) {
            offset += read;
        }
        return offset;
    }
}
