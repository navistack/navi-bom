package org.navistack.framework.objectstorage.namebuilders;

import org.junit.jupiter.api.Test;
import org.navistack.framework.utils.MediaTypes;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FileHashedFileNameBuilderTest {

    @Test
    void buildWithFilename() {
        FileHashedFileNameBuilder builder = new FileHashedFileNameBuilder();
        String filename = builder.build("src/test/resources/Two Hard Things.txt", MediaTypes.TEXT_PLAIN.getFullType());
        assertThat(filename).isEqualTo("0e/0ebdfaaeb77e012365b7666420097849/Two Hard Things.txt");
    }

    @Test
    void buildWithFilepath() {
        FileHashedFileNameBuilder builder = new FileHashedFileNameBuilder();
        String filename = builder.build(FileSystems.getDefault().getPath("src/test/resources/Silver Bullet.txt"), MediaTypes.TEXT_PLAIN.getFullType());
        assertThat(filename).isEqualTo("53/536ad8490c2a6bdc6950508ec726b1bb/Silver Bullet.txt");
    }
}
