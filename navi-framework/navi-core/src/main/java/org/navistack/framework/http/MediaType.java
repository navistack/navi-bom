package org.navistack.framework.http;

import lombok.Getter;
import org.navistack.framework.utils.Asserts;
import org.navistack.framework.utils.Objects;
import org.navistack.framework.utils.Strings;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
public final class MediaType {
    private final String type;
    private final String subType;
    private final String fullType;
    private final Set<String> extensions;

    public MediaType(String type, String subType, Set<String> extensions) {
        Asserts.state(type, Strings::hasText, "type can not be empty");
        Asserts.state(subType, Strings::hasText, "subType can not be empty");
        Asserts.state(extensions, Objects::isNotNull, "extensions can not be null");

        this.type = type;
        this.subType = subType;
        this.fullType = type + "/" + subType;
        this.extensions = extensions;
    }

    public boolean containsExtension(String extension) {
        return extensions.contains(extension);
    }

    public boolean containsAnyExtension(Collection<String> extensions) {
        if (extensions == null || extensions.isEmpty()) {
            return false;
        }
        for (String extension : extensions) {
            if (this.extensions.contains(extension)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsAnyExtension(String... extensions) {
        if (extensions == null) {
            return false;
        }
        for (String extension : extensions) {
            if (this.extensions.contains(extension)) {
                return true;
            }
        }
        return false;
    }

    public static MediaType of(String type, String subType, Set<String> extensions) {
        return new MediaType(type, subType, extensions);
    }

    public static MediaType of(String type, String subType, Collection<? extends String> extensions) {
        return new MediaType(type, subType, new HashSet<>(extensions));
    }

    public static MediaType of(String type, String subType, String... extensions) {
        if (extensions == null || extensions.length == 0) {
            return new MediaType(type, subType, Collections.emptySet());
        } else {
            return new MediaType(type, subType, new HashSet<>(Arrays.asList(extensions)));
        }
    }

    public static MediaType of(String type, String subType) {
        return new MediaType(type, subType, Collections.emptySet());
    }
}
