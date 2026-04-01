package org.navistack.framework.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class IdentifyingEntity<T> extends AuditingEntity<T> implements IdentifyingProperties<T> {
    private T id;
}
