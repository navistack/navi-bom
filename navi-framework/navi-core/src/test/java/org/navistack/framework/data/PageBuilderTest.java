package org.navistack.framework.data;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

class PageBuilderTest {
    @Test
    void shouldBuildSuccessfully() {
        final Collection<Integer> records = Stream.iterate(1, i -> i + 1)
                .limit(3)
                .toList();
        final int pageNumber = 3;
        final int pageSize = 10;
        final long totalRecords = 23;
        final Page<Integer> page = new PageBuilder<Integer>()
                .records(records)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalRecords(totalRecords)
                .build();
        Assertions.assertThat(page.getRecords())
                .isEqualTo(records);
        Assertions.assertThat(page.getPageNumber())
                .isEqualTo(pageNumber);
        Assertions.assertThat(page.getPageSize())
                .isEqualTo(pageSize);
        Assertions.assertThat(page.getTotalRecords())
                .isEqualTo(totalRecords);
    }

    @Test
    void shouldThrowNullPointerExceptionWhenRecordsIsNull() {
        final Collection<Integer> records = null;
        final int pageNumber = 3;
        final int pageSize = 10;
        final long totalRecords = 23;
        Assertions.assertThatThrownBy(() -> {
            new PageBuilder<Integer>()
                    .records(records)
                    .pageNumber(pageNumber)
                    .pageSize(pageSize)
                    .totalRecords(totalRecords)
                    .build();
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    void shouldCalculatePropertiesWhenOnlyRecordsProvided() {
        final Collection<Integer> records = Stream.iterate(1, i -> i + 1)
                .limit(10)
                .toList();
        final Page<Integer> page = new PageBuilder<Integer>()
                .records(records)
                .build();
        Assertions.assertThat(page.getRecords())
                .isEqualTo(records);
        Assertions.assertThat(page.getPageNumber())
                .isEqualTo(1);
        Assertions.assertThat(page.getPageSize())
                .isEqualTo(records.size());
        Assertions.assertThat(page.getTotalRecords())
                .isEqualTo(records.size());
    }

    @Test
    void shouldCorrectTotalRecordsWhenLessThanRecordSize() {
        final Collection<Integer> records = Stream.iterate(1, i -> i + 1)
                .limit(17)
                .toList();
        final int pageNumber = 1;
        final int pageSize = 20;
        final long totalRecords = 13;
        final Page<Integer> page = new PageBuilder<Integer>()
                .records(records)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalRecords(totalRecords)
                .build();
        Assertions.assertThat(page.getRecords())
                .isEqualTo(records);
        Assertions.assertThat(page.getPageNumber())
                .isEqualTo(pageNumber);
        Assertions.assertThat(page.getPageSize())
                .isEqualTo(pageSize);
        Assertions.assertThat(page.getTotalRecords())
                .isEqualTo(records.size());
    }

    @Test
    void shouldCorrectPageSizeWhenLessThanRecordSize() {
        final Collection<Integer> records = Stream.iterate(1, i -> i + 1)
                .limit(10)
                .toList();
        final int pageNumber = 1;
        final int pageSize = 5;
        final long totalRecords = 13;
        final Page<Integer> page = new PageBuilder<Integer>()
                .records(records)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalRecords(totalRecords)
                .build();
        Assertions.assertThat(page.getRecords())
                .isEqualTo(records);
        Assertions.assertThat(page.getPageNumber())
                .isEqualTo(pageNumber);
        Assertions.assertThat(page.getPageSize())
                .isEqualTo(records.size());
        Assertions.assertThat(page.getTotalRecords())
                .isEqualTo(totalRecords);
    }

    @Test
    void shouldCorrectPageNumberWhenExceedMaximumAndRecordsIsNotEmpty() {
        final Collection<Integer> records = Stream.iterate(1, i -> i + 1)
                .limit(10)
                .toList();
        final int pageNumber = 10;
        final int pageSize = 10;
        final long totalRecords = 30;
        final Page<Integer> page = new PageBuilder<Integer>()
                .records(records)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalRecords(totalRecords)
                .build();
        Assertions.assertThat(page.getRecords())
                .isEqualTo(records);
        Assertions.assertThat(page.getPageNumber())
                .isEqualTo(3);
        Assertions.assertThat(page.getPageSize())
                .isEqualTo(pageSize);
        Assertions.assertThat(page.getTotalRecords())
                .isEqualTo(totalRecords);
    }

    @Test
    void shouldNotCorrectPageNumberWhenExceedMaximumAndRecordsIsEmpty() {
        final Collection<Integer> records = Collections.emptyList();
        final int pageNumber = 10;
        final int pageSize = 10;
        final long totalRecords = 30;
        final Page<Integer> page = new PageBuilder<Integer>()
                .records(records)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalRecords(totalRecords)
                .build();
        Assertions.assertThat(page.getRecords())
                .isEqualTo(records);
        Assertions.assertThat(page.getPageNumber())
                .isEqualTo(pageNumber);
        Assertions.assertThat(page.getPageSize())
                .isEqualTo(pageSize);
        Assertions.assertThat(page.getTotalRecords())
                .isEqualTo(totalRecords);
    }

    @Test
    void shouldCollectSuccessfully() {
        final int pageNumber = 1;
        final int pageSize = 10;
        final long totalRecords = 30;
        final PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNumber(pageNumber);
        pageRequest.setPageSize(pageSize);
        final Collection<Integer> records = Stream.iterate(1, i -> i + 1)
                .limit(10)
                .toList();
        Page<Integer> page = records.stream()
                .collect(PageBuilder.collector(pageRequest, totalRecords));
        Assertions.assertThat(page.getRecords())
                .isEqualTo(records);
        Assertions.assertThat(page.getPageNumber())
                .isEqualTo(pageNumber);
        Assertions.assertThat(page.getPageSize())
                .isEqualTo(pageSize);
        Assertions.assertThat(page.getTotalRecords())
                .isEqualTo(totalRecords);
    }
}
