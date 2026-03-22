package org.navistack.boot.logging.autoconfigure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(OperationLogConfiguration.class)
public class LoggingAutoConfiguration {
}
