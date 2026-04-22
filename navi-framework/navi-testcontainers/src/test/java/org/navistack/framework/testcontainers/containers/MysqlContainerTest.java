package org.navistack.framework.testcontainers.containers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MysqlContainerTest {

    @Test
    void shouldUseProvidedTag() {
        try (MysqlContainer container = new MysqlContainer("8.4")) {

            assertThat(container.getDockerImageName()).isEqualTo("mysql:8.4");
        }
    }

    @Test
    void shouldConfigureRootPasswordWhenUsernameIsRoot() {
        try (MysqlContainer container = new MysqlContainer("8.4")) {
            container.setUsername("root");
            container.setPassword("root-password");
            container.configure();

            assertThat(container.getEnvMap())
                    .containsEntry("MYSQL_ROOT_PASSWORD", "root-password")
                    .doesNotContainKey("MYSQL_ALLOW_EMPTY_PASSWORD");
        }
    }

    @Test
    void shouldAllowEmptyRootPasswordWhenConfiguredBlank() {
        try (MysqlContainer container = new MysqlContainer("8.4")) {
            container.setRootPassword("   ");
            container.configure();

            assertThat(container.getEnvMap())
                    .containsEntry("MYSQL_ALLOW_EMPTY_PASSWORD", "yes")
                    .doesNotContainKey("MYSQL_ROOT_PASSWORD");
        }
    }
}
