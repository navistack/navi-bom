package org.navistack.framework.data;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.navistack.framework.utils.Maths;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

@Getter
@Setter
@Accessors(fluent = true)
public class PageBuilder<T> {
    @SuppressWarnings("rawtypes")
    private static final Page EMPTY_PAGE = new PageImpl<>(Collections.emptyList());

    private Collection<T> records;
    private int pageNumber;
    private int pageSize;
    private long totalRecords;

    public Page<T> build() {
        if (records == null) {
            throw new NullPointerException("records must not be null");
        }

        int recordSize = records.size();
        long totalRecords = Math.max(recordSize, this.totalRecords);

        int pageSize = Math.max(recordSize, this.pageSize);

        int maxPageNumber = pageSize > 0
                ? (int) Maths.ceilDiv(totalRecords, pageSize)
                : Integer.MAX_VALUE;
        int pageNumber = recordSize > 0
                ? Maths.clamp(this.pageNumber, 1, maxPageNumber)
                : Math.max(this.pageNumber, 1);

        return new PageImpl<>(records, pageNumber, pageSize, totalRecords);
    }

    public Collector<T, ?, Page<T>> toCollector() {
        return new Collector<T, Collection<T>, Page<T>>() {
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
            public Function<Collection<T>, Page<T>> finisher() {
                return ts -> {
                    PageBuilder.this.records(ts);
                    return PageBuilder.this.build();
                };
            }

            @Override
            public Set<Characteristics> characteristics() {
                return Collections.unmodifiableSet(EnumSet.of(
                        Characteristics.UNORDERED
                ));
            }
        };
    }

    public static <T> Collector<T, ?, Page<T>> collector(int pageNumber, int pageSize) {
        return new PageBuilder<T>()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .toCollector();
    }

    public static <T> Collector<T, ?, Page<T>> collector(int pageNumber, int pageSize, long totalRecords) {
        return new PageBuilder<T>()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalRecords(totalRecords)
                .toCollector();
    }

    public static <T> Collector<T, ?, Page<T>> collector(Pageable pageable, long totalRecords) {
        return new PageBuilder<T>()
                .pageNumber(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .totalRecords(totalRecords)
                .toCollector();
    }

    @SuppressWarnings("unchecked")
    public static <T> Page<T> emptyPage() {
        return (Page<T>) EMPTY_PAGE;
    }
}
