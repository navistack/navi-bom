package org.navistack.boot.autoconfigure.logging;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(OperationLogConfiguration.class)
public class LoggingAutoConfiguration {
}
