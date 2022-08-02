package org.navistack.framework.cache;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StringCacheKeyBuilderTest {
    @Test
    public void build() {
        StringCacheKeyBuilder builder = new StringCacheKeyBuilder(".");
        Assertions.assertThat(builder.build("part1")).isEqualTo("part1");
        Assertions.assertThat(builder.build("part1", "part2", "part3")).isEqualTo("part1.part2.part3");
    }

    @Test
    public void buildWithPrefix() {
        StringCacheKeyBuilder builder = new StringCacheKeyBuilder(".", "prefix");
        Assertions.assertThat(builder.build("part1")).isEqualTo("prefix.part1");
        Assertions.assertThat(builder.build("part1", "part2", "part3")).isEqualTo("prefix.part1.part2.part3");
    }

    @Test
    public void buildWithMultipartPrefix() {
        StringCacheKeyBuilder builder = new StringCacheKeyBuilder(".", new String[]{"prefix1", "prefix2"});
        Assertions.assertThat(builder.build("part1")).isEqualTo("prefix1.prefix2.part1");
        Assertions.assertThat(builder.build("part1", "part2", "part3")).isEqualTo("prefix1.prefix2.part1.part2.part3");
    }

    @Test
    public void buildWithPrefixAndSuffix() {
        StringCacheKeyBuilder builder = new StringCacheKeyBuilder(".", "prefix", "suffix");
        Assertions.assertThat(builder.build("part1")).isEqualTo("prefix.part1.suffix");
        Assertions.assertThat(builder.build("part1", "part2", "part3")).isEqualTo("prefix.part1.part2.part3.suffix");
    }
}
