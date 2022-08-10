package org.navistack.boot.autoconfigure.objectstorage;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(FilesystemProperties.class)
public class FilesystemConfiguration {
}
