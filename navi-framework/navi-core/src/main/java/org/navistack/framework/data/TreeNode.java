package org.navistack.framework.data;

import java.util.Collection;

public interface TreeNode<IdT, T extends TreeNode<IdT, T>> {
    IdT getId();

    void setId(IdT idT);

    IdT getParentId();

    void setParentId(IdT parentId);

    Collection<T> getChildren();

    void setChildren(Collection<T> children);

    void addChild(T child);

    void addChildren(Collection<? extends T> children);
}
