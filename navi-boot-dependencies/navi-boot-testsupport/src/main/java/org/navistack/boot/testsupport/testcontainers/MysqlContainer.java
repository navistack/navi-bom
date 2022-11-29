package org.navistack.boot.testsupport.testcontainers;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class MysqlContainer extends GenericContainer<MysqlContainer> {
    private static final DockerImageName DEFAULT_IMAGE_NAME = DockerImageName.parse("mysql");

    private static final int MYSQL_PORT = 3306;

    private static final String MYSQL_ROOT_USER = "root";

    private static final String DEFAULT_DATABASE = "test";

    private static final String DEFAULT_USERNAME = "test";

    private static final String DEFAULT_PASSWORD = "yK8UcRhm";

    private static final String DEFAULT_ROOT_PASSWORD = "ad4vSPhV";

    @Getter
    @Setter
    @NonNull
    private String database = DEFAULT_DATABASE;

    @Getter
    @Setter
    @NonNull
    private String username = DEFAULT_USERNAME;

    @Getter
    @Setter
    @NonNull
    private String password = DEFAULT_PASSWORD;

    @Getter
    @Setter
    private String rootPassword = DEFAULT_ROOT_PASSWORD;

    public MysqlContainer() {
        super(DEFAULT_IMAGE_NAME);
        addExposedPorts(MYSQL_PORT);
    }

    @Override
    protected void configure() {
        String database = getDatabase();
        addEnv("MYSQL_DATABASE", database);

        String username = getUsername();
        String password = getPassword();
        addEnv("MYSQL_USER", username);
        addEnv("MYSQL_PASSWORD", password);
        if (MYSQL_ROOT_USER.equalsIgnoreCase(username)) {
            addEnv("MYSQL_ROOT_PASSWORD", password);
        } else {
            String rootPassword = getRootPassword();
            if (rootPassword == null || rootPassword.trim().isEmpty()) {
                addEnv("MYSQL_ALLOW_EMPTY_PASSWORD", "yes");
            } else {
                addEnv("MYSQL_ROOT_PASSWORD", rootPassword);
            }
        }
    }

    public String getJdbcUrl() {
        String host = getHost();
        Integer port = getMappedPort(MYSQL_PORT);
        String database = getDatabase();
        return String.format("jdbc:mysql://%s:%d/%s", host, port, database);
    }
}
