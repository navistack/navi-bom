package org.navistack.smoketest.testcontainers;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.navistack.framework.testcontainers.containers.MysqlContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers(disabledWithoutDocker = true)
class MysqlContainerIntegrationTest {

    @Container
    static final MysqlContainer mysqlContainer = new MysqlContainer("8.4");

    @Test
    @SneakyThrows
    void shouldStartMysqlContainerAndProvideJdbcUrl() {
        assertThat(mysqlContainer.isRunning()).isTrue();
        assertThat(mysqlContainer.getFirstMappedPort()).isPositive();
        assertThat(mysqlContainer.getJdbcUrl()).startsWith("jdbc:mysql://");
        assertThat(mysqlContainer.getJdbcUrl()).contains("/test");

        String jdbcUrl = mysqlContainer.getJdbcUrl();
        String username = mysqlContainer.getUsername();
        String password = mysqlContainer.getPassword();
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            DatabaseMetaData metadata = connection.getMetaData();
            String version = metadata.getDatabaseProductVersion();
            assertThat(version).startsWith("8.4");
        }
    }
}
