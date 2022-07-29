package org.navistack.framework.locking;

import java.time.Duration;

public interface PessimisticLockService {
    boolean tryLock(String key, Duration timeout);

    boolean unlock(String key);
}
