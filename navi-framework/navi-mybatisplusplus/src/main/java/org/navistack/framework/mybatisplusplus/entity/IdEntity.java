package org.navistack.framework.mybatisplusplus.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class IdEntity<T> extends AuditingEntity<T> {
    private T id;
}
