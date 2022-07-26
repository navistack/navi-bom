package org.navistack.framework.data;

import java.time.Instant;

public interface AuditingProperties<T> {
    Instant getCreatedAt();

    Instant getUpdatedAt();

    Instant getDeletedAt();

    T getCreatedBy();

    T getUpdatedBy();

    T getDeletedBy();
}
