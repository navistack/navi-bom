package org.navistack.framework.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class HierarchicalGeoDivision
        extends AbstractTreeNode<String, HierarchicalGeoDivision>
        implements TreeNode<String, HierarchicalGeoDivision> {
    private String code;
    private String name;
    private String type;
    private String parent;

    public HierarchicalGeoDivision(String code, String name, String type, String parent, Collection<HierarchicalGeoDivision> children) {
        super(children);

        this.code = code;
        this.name = name;
        this.type = type;
        this.parent = parent;
    }

    @Override
    public String getId() {
        return code;
    }

    @Override
    public String getParentId() {
        return parent;
    }

    public static HierarchicalGeoDivision of(String code, String name, String type, String parent) {
        return new HierarchicalGeoDivision(code, name, type, parent);
    }

    public static HierarchicalGeoDivision of(String code, String name, String type, String parent, Collection<HierarchicalGeoDivision> children) {
        return new HierarchicalGeoDivision(code, name, type, parent, children);
    }

    public static HierarchicalGeoDivision of(String code, String name, String type) {
        return of(code, name, type, (String) null);
    }

    public static HierarchicalGeoDivision of(String code, String name, String type, Collection<HierarchicalGeoDivision> children) {
        return of(code, name, type, null, children);
    }

    public static HierarchicalGeoDivision of(GeoDivision geoDivision) {
        return of(
                geoDivision.getCode(),
                geoDivision.getName(),
                geoDivision.getType(),
                geoDivision.getParent()
        );
    }
}
