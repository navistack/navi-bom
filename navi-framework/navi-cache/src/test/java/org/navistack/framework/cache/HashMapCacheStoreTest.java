package org.navistack.framework.cache;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class HashMapCacheStoreTest {
    @Test
    void shouldGetValueWhenValueWasSet() {
        HashMapCacheStore store = new HashMapCacheStore();
        store.set("key", "value");
        assertThat(store.get("key", String.class)).isEqualTo("value");
    }

    @Test
    void shouldReturnNullWhenValueExpired() {
        HashMapCacheStore store = new HashMapCacheStore();
        store.setClock(Clock.fixed(Instant.parse("2011-12-03T10:15:30Z"), ZoneId.of("UTC")));
        store.set("key", "value", Duration.ZERO);
        store.setClock(Clock.fixed(Instant.parse("2011-12-03T10:15:31Z"), ZoneId.of("UTC")));
        assertThat(store.get("key", String.class)).isNull();
    }

    @Test
    void shouldNotOverrideExistingValueWhenSetIfAbsentCalled() {
        HashMapCacheStore store = new HashMapCacheStore();
        store.setIfAbsent("key", "value");
        assertThat(store.setIfAbsent("key", "new value")).isFalse();
        assertThat(store.setIfAbsent("new key", "new value")).isTrue();
        assertThat(store.get("key", String.class)).isEqualTo("value");
        assertThat(store.get("new key", String.class)).isEqualTo("new value");
    }

    @Test
    void shouldSetNewValueWhenExistingValueExpiredAndSetIfAbsentCalled() {
        HashMapCacheStore store = new HashMapCacheStore();
        store.setClock(Clock.fixed(Instant.parse("2011-12-03T10:15:30Z"), ZoneId.of("UTC")));
        assertThat(store.setIfAbsent("key", "value", Duration.ZERO)).isTrue();
        store.setClock(Clock.fixed(Instant.parse("2011-12-03T10:15:31Z"), ZoneId.of("UTC")));
        assertThat(store.setIfAbsent("key", "new value")).isTrue();
        assertThat(store.get("key", String.class)).isEqualTo("new value");
    }

    @Test
    void shouldThrowClassCastExceptionWhenGettingWithWrongType() {
        HashMapCacheStore store = new HashMapCacheStore();
        store.set("key", "value");
        assertThat(store.get("key", String.class)).isEqualTo("value");
        Assertions.assertThatThrownBy(() -> store.get("key", Number.class)).isInstanceOf(ClassCastException.class);
    }

    @Test
    void shouldReturnTrueAndRemoveValueWhenDeleteCalled() {
        HashMapCacheStore store = new HashMapCacheStore();
        store.set("key", "value");
        assertThat(store.get("key", String.class)).isEqualTo("value");
        assertThat(store.delete("key")).isTrue();
        assertThat(store.get("key", String.class)).isNull();
    }

    @Test
    void shouldReturnValueAndRemoveWhenGetAndDeleteCalled() {
        HashMapCacheStore store = new HashMapCacheStore();
        store.set("key", "value");
        assertThat(store.getAndDelete("key", String.class)).isEqualTo("value");
        assertThat(store.getAndDelete("key", String.class)).isNull();
    }

    @Test
    void shouldNotExpireWhenNowEqualsExpiration() {
        HashMapCacheStore store = new HashMapCacheStore();
        store.setClock(Clock.fixed(Instant.parse("2011-12-03T10:15:30Z"), ZoneId.of("UTC")));
        store.set("key", "value", Duration.ofSeconds(1));

        // expiration == now => should still be present because expiration < nowMilli is required to expire
        store.setClock(Clock.fixed(Instant.parse("2011-12-03T10:15:31Z"), ZoneId.of("UTC")));
        assertThat(store.get("key", String.class)).isEqualTo("value");
    }

    @Test
    void shouldReturnTrueWhenDeletingMissingKey() {
        HashMapCacheStore store = new HashMapCacheStore();
        assertThat(store.delete("missing")).isTrue();
    }
}
