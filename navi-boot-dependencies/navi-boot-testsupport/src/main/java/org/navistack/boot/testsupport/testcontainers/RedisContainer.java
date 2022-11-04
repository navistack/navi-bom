package org.navistack.boot.testsupport.testcontainers;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class RedisContainer extends GenericContainer<RedisContainer> {
    private static final DockerImageName DEFAULT_IMAGE_NAME = DockerImageName.parse("redis");

    private static final int DEFAULT_EXPOSED_PORT = 6379;

    public RedisContainer() {
        super(DEFAULT_IMAGE_NAME);
        addExposedPorts(DEFAULT_EXPOSED_PORT);
    }
}
