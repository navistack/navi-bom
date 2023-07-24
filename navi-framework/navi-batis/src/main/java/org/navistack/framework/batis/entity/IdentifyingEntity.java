package org.navistack.framework.batis.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.navistack.framework.data.IdentifyingProperties;

@EqualsAndHashCode(callSuper = true)
@Data
public class IdentifyingEntity<T> extends AuditingEntity<T> implements IdentifyingProperties<T> {
    private T id;
}
