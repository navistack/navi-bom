package org.navistack.framework.objectstorage;

import java.io.File;
import java.nio.file.Path;

public interface FilenameBuilder {
    String build(String filename, String contentType);
    String build(Path filepath, String contentType);
    String build(File file, String contentType);
}
