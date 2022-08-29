package org.navistack.framework.data;

import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Function;

@UtilityClass
public class TreeUtils {
    /**
     * Build a tree from a flat collection
     *
     * @param collection collection of elements to build from
     * @param converter  converter that converts arbitrary type to TreeNode
     * @return tree built
     */
    public <ID, T extends TreeNode<ID, T>, U> Collection<T> treeize(
            Collection<U> collection,
            ID rootId,
            Function<U, T> converter
    ) {
        Collection<T> rootNodes = new LinkedList<>();

        Map<ID, T> nodesMap = new HashMap<>();
        Collection<T> orphanNodes = new LinkedList<>();

        for (U item : collection) {
            T node = converter.apply(item);

            ID id = node.getId();
            ID parentId = node.getParentId();

            nodesMap.putIfAbsent(id, node);

            if (id.equals(parentId) || (rootId == null && parentId == null || (rootId != null && rootId.equals(parentId)))) {
                rootNodes.add(node);
            } else {
                T parentNode = nodesMap.get(parentId);
                if (parentNode == null) {
                    orphanNodes.add(node);
                } else {
                    parentNode.getChildren().add(node);
                }
            }
        }

        for (T node : orphanNodes) {
            ID parentId = node.getParentId();

            T parentNode = nodesMap.get(parentId);

            // If parentNode not present,
            // then this node is definitely orphan,
            // and we will just omit it.
            if (parentNode != null) {
                parentNode.getChildren().add(node);
            }
        }

        return rootNodes;
    }

    public <ID, T extends TreeNode<ID, T>, U> Collection<T> treeize(Collection<U> collection, Function<U, T> converter) {
        return treeize(collection, null, converter);
    }

    public <ID, T extends TreeNode<ID, T>> Collection<T> treeize(Collection<T> collection, ID rootId) {
        return treeize(collection, rootId, Function.identity());
    }

    public <ID, T extends TreeNode<ID, T>> Collection<T> treeize(Collection<T> collection) {
        return treeize(collection, null, Function.identity());
    }
}
