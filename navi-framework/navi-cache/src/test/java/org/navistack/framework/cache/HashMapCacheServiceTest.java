package org.navistack.framework.cache;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class HashMapCacheServiceTest {
    @Test
    void set() {
        HashMapCacheService service = new HashMapCacheService();
        service.set("key", "value");
        assertThat(service.get("key", String.class)).isEqualTo("value");
    }

    @Test
    void setWithDuration() {
        HashMapCacheService service = new HashMapCacheService();
        service.setClock(Clock.fixed(Instant.parse("2011-12-03T10:15:30Z"), ZoneId.of("UTC")));
        service.set("key", "value", Duration.ZERO);
        service.setClock(Clock.fixed(Instant.parse("2011-12-03T10:15:31Z"), ZoneId.of("UTC")));
        assertThat(service.get("key", String.class)).isNull();
    }

    @Test
    void setIfAbsent() {
        HashMapCacheService service = new HashMapCacheService();
        service.setIfAbsent("key", "value");
        assertThat(service.setIfAbsent("key", "new value")).isFalse();
        assertThat(service.setIfAbsent("new key", "new value")).isTrue();
        assertThat(service.get("key", String.class)).isEqualTo("value");
        assertThat(service.get("new key", String.class)).isEqualTo("new value");
    }

    @Test
    void setIfAbsentWithDuration() {
        HashMapCacheService service = new HashMapCacheService();
        service.setClock(Clock.fixed(Instant.parse("2011-12-03T10:15:30Z"), ZoneId.of("UTC")));
        assertThat(service.setIfAbsent("key", "value", Duration.ZERO)).isTrue();
        service.setClock(Clock.fixed(Instant.parse("2011-12-03T10:15:31Z"), ZoneId.of("UTC")));
        assertThat(service.setIfAbsent("key", "new value")).isTrue();
        assertThat(service.get("key", String.class)).isEqualTo("new value");
    }

    @Test
    void get() {
        HashMapCacheService service = new HashMapCacheService();
        service.set("key", "value");
        assertThat(service.get("key", String.class)).isEqualTo("value");
        Assertions.assertThatThrownBy(() -> service.get("key", Number.class)).isInstanceOf(ClassCastException.class);
    }

    @Test
    void delete() {
        HashMapCacheService service = new HashMapCacheService();
        service.set("key", "value");
        assertThat(service.get("key", String.class)).isEqualTo("value");
        service.delete("key");
        assertThat(service.get("key", String.class)).isNull();
    }

    @Test
    void getAndDelete() {
        HashMapCacheService service = new HashMapCacheService();
        service.set("key", "value");
        assertThat(service.getAndDelete("key", String.class)).isEqualTo("value");
        assertThat(service.getAndDelete("key", String.class)).isNull();
    }
}
