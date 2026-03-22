package org.navistack.boot.locking.autoconfigure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PessimisticLockConfiguration.class)
public class LockingAutoConfiguration {
}
