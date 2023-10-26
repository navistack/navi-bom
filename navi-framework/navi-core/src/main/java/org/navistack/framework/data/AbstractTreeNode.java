package org.navistack.framework.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Data
public abstract class AbstractTreeNode<IdT, T extends TreeNode<IdT, T>> implements TreeNode<IdT, T> {
    private Collection<T> children;

    public AbstractTreeNode() {
        this(new ArrayList<>());
    }

    public AbstractTreeNode(Collection<T> children) {
        if (children == null) {
            throw new NullPointerException("children is null");
        }
        this.children = children;
    }

    @Override
    public void addChild(T child) {
        if (child == null) {
            throw new NullPointerException("child is null");
        }
        children.add(child);
    }

    @Override
    public void addChildren(Collection<? extends T> children) {
        if (children == null) {
            throw new NullPointerException("children is null");
        }
        if (children.isEmpty()) {
            return;
        }
        this.children.addAll(children);
    }
}
