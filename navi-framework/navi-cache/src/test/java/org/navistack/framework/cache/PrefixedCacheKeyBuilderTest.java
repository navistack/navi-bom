package org.navistack.framework.cache;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PrefixedCacheKeyBuilderTest {
    @Test
    public void build() {
        PrefixedCacheKeyBuilder builder = new PrefixedCacheKeyBuilder(".");
        Assertions.assertThat(builder.build("part1")).isEqualTo("part1");
        Assertions.assertThat(builder.build("part1", "part2", "part3")).isEqualTo("part1.part2.part3");
    }

    @Test
    public void buildWithPrefix() {
        PrefixedCacheKeyBuilder builder = new PrefixedCacheKeyBuilder(".", "prefix");
        Assertions.assertThat(builder.build("part1")).isEqualTo("prefix.part1");
        Assertions.assertThat(builder.build("part1", "part2", "part3")).isEqualTo("prefix.part1.part2.part3");
    }

    @Test
    public void buildWithMultipartPrefix() {
        PrefixedCacheKeyBuilder builder = new PrefixedCacheKeyBuilder(".", "prefix1", "prefix2");
        Assertions.assertThat(builder.build("part1")).isEqualTo("prefix1.prefix2.part1");
        Assertions.assertThat(builder.build("part1", "part2", "part3")).isEqualTo("prefix1.prefix2.part1.part2.part3");
    }
}
