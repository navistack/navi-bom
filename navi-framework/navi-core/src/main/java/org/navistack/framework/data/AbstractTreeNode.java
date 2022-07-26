package org.navistack.framework.data;

import lombok.Data;

import java.util.Collection;
import java.util.LinkedList;

@Data
public abstract class AbstractTreeNode<ID, T extends TreeNode<ID, T>> implements TreeNode<ID, T> {
    private Collection<T> children;

    public AbstractTreeNode() {
        this(new LinkedList<>());
    }

    public AbstractTreeNode(Collection<T> children) {
        this.children = children;
    }
}
