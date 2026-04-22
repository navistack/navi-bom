package org.navistack.smoketest.testcontainers;

import org.junit.jupiter.api.Test;
import org.navistack.framework.testcontainers.containers.RedisContainer;
import redis.clients.jedis.Jedis;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers(disabledWithoutDocker = true)
class RedisContainerIntegrationTest {

    @Container
    static final RedisContainer redisContainer = new RedisContainer("8.2");

    @Test
    void shouldStartRedisContainerAndExposePort() {
        assertThat(redisContainer.isRunning()).isTrue();
        assertThat(redisContainer.getFirstMappedPort()).isPositive();
        assertThat(redisContainer.getHost()).isNotBlank();

        try (Jedis jedis = new Jedis(redisContainer.getHost(), redisContainer.getFirstMappedPort())) {
            String version = jedis.info("server")
                    .lines()
                    .map(String::trim)
                    .map(line -> line.split(":", 2))
                    .filter(parts -> parts.length == 2)
                    .filter(parts -> "redis_version".equals(parts[0]))
                    .map(parts -> parts[1].trim())
                    .findFirst()
                    .orElse("");
            assertThat(version).isNotBlank();
            assertThat(version).startsWith("8.2");
        }
    }
}
