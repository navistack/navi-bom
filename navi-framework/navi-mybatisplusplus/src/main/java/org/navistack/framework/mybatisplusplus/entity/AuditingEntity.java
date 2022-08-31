package org.navistack.framework.mybatisplusplus.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.navistack.framework.data.AuditingProperties;

import java.time.Instant;

@Data
public class AuditingEntity<T> implements AuditingProperties<T> {
    @TableField(fill = FieldFill.INSERT)
    private Instant createdAt;

    @TableField(fill = FieldFill.INSERT)
    private T createdBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Instant updatedAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private T updatedBy;

    private boolean deleted;

    private Instant deletedAt;

    private T deletedBy;
}
