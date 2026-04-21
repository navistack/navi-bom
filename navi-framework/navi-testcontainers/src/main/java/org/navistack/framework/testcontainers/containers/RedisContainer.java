package org.navistack.framework.testcontainers.containers;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class RedisContainer extends GenericContainer<RedisContainer> {
    private static final DockerImageName DEFAULT_IMAGE_NAME = DockerImageName.parse("redis");

    private static final int DEFAULT_EXPOSED_PORT = 6379;

    public RedisContainer(DockerImageName imageName) {
        super(imageName);
        addExposedPorts(DEFAULT_EXPOSED_PORT);
    }

    public RedisContainer(String tag) {
        this(DEFAULT_IMAGE_NAME.withTag(tag));
    }

    public RedisContainer() {
        this(DEFAULT_IMAGE_NAME);
    }
}
