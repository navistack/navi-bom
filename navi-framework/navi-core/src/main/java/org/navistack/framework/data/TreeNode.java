package org.navistack.framework.data;

import java.util.Collection;

public interface TreeNode<ID, T extends TreeNode<ID, T>> {
    ID getId();

    ID getParentId();

    Collection<T> getChildren();
}
