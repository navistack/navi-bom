package org.navistack.framework.data;

public interface IdentifyingProperties<T> extends AuditingProperties<T> {
    T getId();

    void setId(T id);
}
