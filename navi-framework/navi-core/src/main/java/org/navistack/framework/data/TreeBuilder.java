package org.navistack.framework.data;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Build a tree from a flat collection.
 * Requires identifier to be unique across any level.
 */
@Getter
@Setter
@Accessors(fluent = true)
public class TreeBuilder<S, T> {
    private Function<S, ?> idMapper;

    private Function<S, ?> parentIdMapper;

    private Function<S, T> mapper;

    private BiConsumer<T, T> childPicker;

    private Consumer<T> orphanAdopter = t -> {
    };

    private Predicate<S> rootPredicate = it -> parentIdMapper != null && Objects.isNull(parentIdMapper.apply(it));

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final Map<Object, T> nodeMap = new HashMap<>();

    public Collection<T> build(Collection<? extends S> collection) {
        if (collection == null) {
            throw new NullPointerException("collection must not be null");
        }

        if (collection.isEmpty()) {
            return Collections.emptyList();
        }

        if (this.idMapper == null) {
            throw new NullPointerException("idMapper must not be null");
        }

        if (this.parentIdMapper == null) {
            throw new NullPointerException("parentIdMapper must not be null");
        }

        if (this.mapper == null) {
            throw new NullPointerException("mapper must not be null");
        }

        if (this.childPicker == null) {
            throw new NullPointerException("childPicker must not be null");
        }

        Collection<T> forest = new ArrayList<>();

        Collection<S> orphans = new ArrayList<>();
        for (S item : collection) {
            T mappedItem = mapper.apply(item);

            Object id = idMapper.apply(item);
            T prevNode = nodeMap.putIfAbsent(id, mappedItem);
            if (prevNode != null) {
                throw new IllegalStateException(
                        String.format(
                                "Duplicate id %s (attempted merging values %s and %s)",
                                id,
                                prevNode,
                                item
                        )
                );
            }

            if (rootPredicate.test(item)) {
                forest.add(mappedItem);
            } else {
                Object parentId = parentIdMapper.apply(item);
                if (nodeMap.containsKey(parentId)) {
                    T parent = nodeMap.get(parentId);
                    childPicker.accept(parent, mappedItem);
                } else {
                    orphans.add(item);
                }
            }
        }

        for (S orphan : orphans) {
            Object id = idMapper.apply(orphan);
            Object parentId = parentIdMapper.apply(orphan);
            if (nodeMap.containsKey(parentId)) {
                T mappedOrphan = nodeMap.get(id);
                T parent = nodeMap.get(parentId);
                childPicker.accept(parent, mappedOrphan);
            } else {
                T mappedOrphan = nodeMap.get(id);
                orphanAdopter.accept(mappedOrphan);
            }
        }

        return forest;
    }

    public Collector<S, ?, Collection<T>> toCollector() {
        return new Collector<S, Collection<S>, Collection<T>>() {
            @Override
            public Supplier<Collection<S>> supplier() {
                return ArrayList::new;
            }

            @Override
            public BiConsumer<Collection<S>, S> accumulator() {
                return Collection::add;
            }

            @Override
            public BinaryOperator<Collection<S>> combiner() {
                return (left, right) -> {
                    left.addAll(right);
                    return left;
                };
            }

            @Override
            public Function<Collection<S>, Collection<T>> finisher() {
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

    public static <S, T> TreeBuilder<S, T> of(
            Function<S, ?> idMapper,
            Function<S, ?> parentIdMapper,
            Function<S, T> mapper,
            BiConsumer<T, T> childPicker
    ) {
        return new TreeBuilder<S, T>()
                .idMapper(idMapper)
                .parentIdMapper(parentIdMapper)
                .mapper(mapper)
                .childPicker(childPicker);
    }

    public static <S, T extends TreeNode<?, T>> TreeBuilder<S, T> of(
            Function<S, ?> idMapper,
            Function<S, ?> parentIdMapper,
            Function<S, T> mapper
    ) {
        return new TreeBuilder<S, T>()
                .idMapper(idMapper)
                .parentIdMapper(parentIdMapper)
                .mapper(mapper)
                .childPicker(TreeNode::addChild);
    }

    public static <S, T extends TreeNode<?, T>> TreeBuilder<S, T> of(
            Function<S, ?> idMapper,
            Function<S, ?> parentIdMapper,
            Function<S, T> mapper,
            Predicate<S> rootPredicate

    ) {
        return new TreeBuilder<S, T>()
                .idMapper(idMapper)
                .parentIdMapper(parentIdMapper)
                .mapper(mapper)
                .childPicker(TreeNode::addChild)
                .rootPredicate(rootPredicate);
    }

    public static <T extends TreeNode<?, T>> TreeBuilder<T, T> of(
            Predicate<T> rootPredicate
    ) {
        return new TreeBuilder<T, T>()
                .idMapper(TreeNode::getId)
                .parentIdMapper(TreeNode::getParentId)
                .mapper(Function.identity())
                .rootPredicate(rootPredicate)
                .childPicker(TreeNode::addChild);
    }

    public static <T extends TreeNode<?, T>> TreeBuilder<T, T> of() {
        return new TreeBuilder<T, T>()
                .idMapper(TreeNode::getId)
                .parentIdMapper(TreeNode::getParentId)
                .mapper(Function.identity())
                .rootPredicate(n -> Objects.isNull(n.getParentId()))
                .childPicker(TreeNode::addChild);
    }

    public static <S, T> Collector<S, ?, Collection<T>> collector(
            Function<S, ?> idMapper,
            Function<S, ?> parentIdMapper,
            Function<S, T> mapper,
            BiConsumer<T, T> childPicker
    ) {
        return new TreeBuilder<S, T>()
                .idMapper(idMapper)
                .parentIdMapper(parentIdMapper)
                .mapper(mapper)
                .childPicker(childPicker)
                .toCollector();
    }

    public static <T extends TreeNode<?, T>> Collector<T, ?, Collection<T>> collector() {
        return new TreeBuilder<T, T>()
                .idMapper(TreeNode::getId)
                .parentIdMapper(TreeNode::getParentId)
                .mapper(Function.identity())
                .rootPredicate(n -> Objects.isNull(n.getParentId()))
                .childPicker(TreeNode::addChild)
                .toCollector();
    }
}
