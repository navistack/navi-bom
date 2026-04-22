package org.navistack.framework.testcontainers.containers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RedisContainerTest {

    @Test
    void shouldUseProvidedTag() {
        try (RedisContainer container = new RedisContainer("8.2")) {

            assertThat(container.getDockerImageName()).isEqualTo("redis:8.2");
        }
    }
}
