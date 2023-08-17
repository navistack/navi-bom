package org.navistack.framework.data;

import java.util.Collection;

public interface TreeNode<ID, T extends TreeNode<ID, T>> {
    ID getId();

    void setId(ID id);

    ID getParentId();

    void setParentId(ID parentId);

    Collection<T> getChildren();

    void setChildren(Collection<T> children);

    void addChild(T child);

    void addChildren(Collection<? extends T> children);
}
