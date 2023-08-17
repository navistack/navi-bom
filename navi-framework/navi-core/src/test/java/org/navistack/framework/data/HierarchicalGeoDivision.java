package org.navistack.framework.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class HierarchicalGeoDivision
        extends AbstractTreeNode<String, HierarchicalGeoDivision>
        implements TreeNode<String, HierarchicalGeoDivision> {
    private String id;
    private String name;
    private String type;
    private String parentId;

    public HierarchicalGeoDivision(String id, String name, String type, String parentId, Collection<HierarchicalGeoDivision> children) {
        super(children);

        this.id = id;
        this.name = name;
        this.type = type;
        this.parentId = parentId;
    }

    public HierarchicalGeoDivision(String id, String name, String type, String parentId) {
        super();

        this.id = id;
        this.name = name;
        this.type = type;
        this.parentId = parentId;
    }

    public HierarchicalGeoDivision(String id, String name, String type, Collection<HierarchicalGeoDivision> children) {
        super(children);

        this.id = id;
        this.name = name;
        this.type = type;
    }

    public HierarchicalGeoDivision(String id, String name, String type) {
        super();

        this.id = id;
        this.name = name;
        this.type = type;
    }

    public static HierarchicalGeoDivision of(String id, String name, String type, String parentId) {
        return new HierarchicalGeoDivision(id, name, type, parentId);
    }

    public static HierarchicalGeoDivision of(String id, String name, String type, String parentId, Collection<HierarchicalGeoDivision> children) {
        return new HierarchicalGeoDivision(id, name, type, parentId, children);
    }

    public static HierarchicalGeoDivision of(String id, String name, String type) {
        return new HierarchicalGeoDivision(id, name, type);
    }

    public static HierarchicalGeoDivision of(String id, String name, String type, Collection<HierarchicalGeoDivision> children) {
        return new HierarchicalGeoDivision(id, name, type, children);
    }

    public static HierarchicalGeoDivision of(GeoDivision geoDivision) {
        String code = geoDivision.getId();
        String name = geoDivision.getName();
        String type = geoDivision.getType();
        String parent = geoDivision.getParentId();
        return new HierarchicalGeoDivision(code, name, type, parent);
    }
}
