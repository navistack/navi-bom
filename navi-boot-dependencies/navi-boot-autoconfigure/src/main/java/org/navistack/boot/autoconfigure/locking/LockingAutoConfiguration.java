package org.navistack.boot.autoconfigure.locking;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PessimisticLockConfiguration.class)
public class LockingAutoConfiguration {
}
