package org.navistack.framework.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Build a tree from a flat collection.
 * Requires identifier to be unique across any level.
 */
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
public class TreeBuilder<T> {

    @Getter
    @Setter
    private Function<T, ?> identifier;

    @Getter
    @Setter
    private Function<T, ?> parentIdentifier;

    @Getter
    @Setter
    private Function<T, Collection<T>> childCollectionFactory;

    @Getter
    @Setter
    private boolean ignoreDuplicate = true;

    @Getter
    @Setter
    private boolean orphanAsRoot = false;

    private final BiConsumer<T, T> childConsumer = (parent, child) -> {
        Collection<T> siblings = childCollectionFactory.apply(parent);
        if (siblings == null) {
            throw new NullPointerException("childCollectionFactory returned null");
        }
        siblings.add(child);
    };

    private final Map<Object, T> objectMap = new HashMap<>();

    public Collection<T> build(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return Collections.emptyList();
        }

        if (this.identifier == null) {
            throw new NullPointerException("identifier must not be null");
        }

        if (this.parentIdentifier == null) {
            throw new NullPointerException("parentIdentifier must not be null");
        }

        if (this.childCollectionFactory == null) {
            throw new NullPointerException("childCollectionFactory must not be null");
        }

        Collection<T> forest = new ArrayList<>();
        Collection<T> orphans = new ArrayList<>();
        for (T item : collection) {
            Object id = identifier.apply(item);
            T previous = objectMap.putIfAbsent(id, item);
            if (previous != null && !ignoreDuplicate) {
                throw new IllegalStateException("Duplicate key");
            }
            Object parentId = parentIdentifier.apply(item);
            if (parentId != null) {
                T parent = objectMap.get(parentId);
                if (parent != null) {
                    childConsumer.accept(parent, item);
                } else {
                    orphans.add(item);
                }
            } else {
                forest.add(item);
            }
        }

        for (T orphan : orphans) {
            Object parentId = parentIdentifier.apply(orphan);
            T parent = objectMap.get(parentId);
            if (parent != null) {
                childConsumer.accept(parent, orphan);
            } else if (orphanAsRoot) {
                forest.add(orphan);
            }
        }

        return forest;
    }

    public Collector<T, ?, Collection<T>> toCollector() {
        return new Collector<T, Collection<T>, Collection<T>>() {
            @Override
            public Supplier<Collection<T>> supplier() {
                return ArrayList::new;
            }

            @Override
            public BiConsumer<Collection<T>, T> accumulator() {
                return Collection::add;
            }

            @Override
            public BinaryOperator<Collection<T>> combiner() {
                return (left, right) -> {
                    left.addAll(right);
                    return left;
                };
            }

            @Override
            public Function<Collection<T>, Collection<T>> finisher() {
                return TreeBuilder.this::build;
            }

            @Override
            public Set<Characteristics> characteristics() {
                return Collections.unmodifiableSet(EnumSet.of(
                        Characteristics.UNORDERED
                ));
            }
        };
    }

    public static <T> TreeBuilder<T> of(
            Function<T, ?> identifier,
            Function<T, ?> parentIdentifier,
            Function<T, Collection<T>> childCollectionFactory
    ) {
        return new TreeBuilder<T>()
                .identifier(identifier)
                .parentIdentifier(parentIdentifier)
                .childCollectionFactory(childCollectionFactory)
                ;
    }

    public static <T extends TreeNode<?, T>> TreeBuilder<T> of() {
        return new TreeBuilder<T>()
                .identifier(TreeNode::getId)
                .parentIdentifier(TreeNode::getParentId)
                .childCollectionFactory(TreeNode::getChildren)
                ;
    }

    public static <T> Collector<T, ?, Collection<T>> collector(
            Function<T, ?> identifier,
            Function<T, ?> parentIdentifier,
            Function<T, Collection<T>> childCollectionFactory
    ) {
        return TreeBuilder.of(identifier, parentIdentifier, childCollectionFactory)
                .toCollector();
    }

    public static <T extends TreeNode<?, T>> Collector<T, ?, Collection<T>> collector() {
        TreeBuilder<T> builder = of();
        return builder.toCollector();
    }
}
