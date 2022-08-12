package org.navistack.framework.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MediaTypeTest {
    @Test
    void properties() {
        MediaType mediaType = new MediaType("video", "mpeg", new HashSet<>(Arrays.asList("mpeg", "mpg", "mpe", "m1v", "m2v")));
        assertThat(mediaType).extracting(MediaType::getType).isEqualTo("video");
        assertThat(mediaType).extracting(MediaType::getSubType).isEqualTo("mpeg");
        assertThat(mediaType).extracting(MediaType::getFullType).isEqualTo("video/mpeg");
        assertThat(mediaType).extracting(MediaType::getExtensions).isEqualTo(new HashSet<>(Arrays.asList("mpeg", "mpg", "mpe", "m1v", "m2v")));
    }

    @Test
    void constructorWithIllegalArguments() {
        assertThatThrownBy(() -> new MediaType(null, "mpeg", new HashSet<>(Arrays.asList("mpeg", "mpg", "mpe", "m1v", "m2v")))).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new MediaType("", "mpeg", new HashSet<>(Arrays.asList("mpeg", "mpg", "mpe", "m1v", "m2v")))).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new MediaType("video", null, new HashSet<>(Arrays.asList("mpeg", "mpg", "mpe", "m1v", "m2v")))).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new MediaType("video", "", new HashSet<>(Arrays.asList("mpeg", "mpg", "mpe", "m1v", "m2v")))).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new MediaType("video", "mpeg", null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void containsExtension() {
        MediaType mediaType = new MediaType("video", "mpeg", new HashSet<>(Arrays.asList("mpeg", "mpg", "mpe", "m1v", "m2v")));
        assertThat(mediaType.containsExtension("mpeg")).isTrue();
        assertThat(mediaType.containsExtension("mpg")).isTrue();
        assertThat(mediaType.containsExtension("mpe")).isTrue();
        assertThat(mediaType.containsExtension("m1v")).isTrue();
        assertThat(mediaType.containsExtension("m2v")).isTrue();
        assertThat(mediaType.containsExtension("m2v")).isTrue();
        assertThat(mediaType.containsExtension("mp4")).isFalse();
        assertThat(mediaType.containsExtension("mp4v")).isFalse();
        assertThat(mediaType.containsExtension("mpg4")).isFalse();
    }

    @Test
    void containsAnyExtension() {
        MediaType mediaType = new MediaType("video", "mpeg", new HashSet<>(Arrays.asList("mpeg", "mpg", "mpe", "m1v", "m2v")));
        assertThat(mediaType.containsAnyExtension(Arrays.asList("mp4", "mp4v", "mpg4", "mpeg"))).isTrue();
        assertThat(mediaType.containsAnyExtension(Arrays.asList("mp4", "mp4v", "mpg4"))).isFalse();
        assertThat(mediaType.containsAnyExtension((Collection<String>) null)).isFalse();
        assertThat(mediaType.containsAnyExtension(Collections.emptyList())).isFalse();
    }

    @Test
    void containsAnyExtensionVarargs() {
        MediaType mediaType = new MediaType("video", "mpeg", new HashSet<>(Arrays.asList("mpeg", "mpg", "mpe", "m1v", "m2v")));
        assertThat(mediaType.containsAnyExtension("mp4", "mp4v", "mpg4", "mpeg")).isTrue();
        assertThat(mediaType.containsAnyExtension("mp4", "mp4v", "mpg4")).isFalse();
        assertThat(mediaType.containsAnyExtension((String[]) null)).isFalse();
        assertThat(mediaType.containsAnyExtension(new String[]{})).isFalse();
    }
}
