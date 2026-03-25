package org.navistack.framework.cache;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CacheScopeTest {

    @Test
    void shouldReturnKeyWithScopeNameWhenScoped() {
        CacheScope scope = CacheScope.of("test");
        assertThat(scope.key("key")).isEqualTo("test:key");
    }

    @Test
    void shouldReturnKeyWithoutPrefixWhenRootScope() {
        CacheScope scope = CacheScope.root();
        assertThat(scope.key("key")).isEqualTo("key");
    }

    @Test
    void shouldUseLeafToRootOrderWhenNestedScopes() {
        CacheScope root = CacheScope.root();
        CacheScope a = root.scope("a");
        CacheScope b = a.scope("b");

        assertThat(b.key("key")).isEqualTo("b:a:key");
    }

    @Test
    void shouldJoinExtraPartsWhenProvided() {
        CacheScope scope = CacheScope.of("test");
        assertThat(scope.key("key", "p1", "p2")).isEqualTo("test:key:p1:p2");
    }
}

