package org.navistack.framework.data;

import java.time.Instant;

public interface AuditingProperties<T> {
    Instant getCreatedAt();

    void setCreatedAt(Instant instant);

    Instant getUpdatedAt();

    void setUpdatedAt(Instant instant);

    Instant getDeletedAt();

    void setDeletedAt(Instant instant);

    T getCreatedBy();

    void setCreatedBy(T maintainer);

    T getUpdatedBy();

    void setUpdatedBy(T maintainer);

    T getDeletedBy();

    void setDeletedBy(T maintainer);
}
