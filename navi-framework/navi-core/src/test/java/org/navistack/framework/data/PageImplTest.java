package org.navistack.framework.data;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class PageImplTest {
    @Test
    void setRecords() {
        assertThat(new PageImpl<>(null, 1, 10, 100).getRecords()).isNotNull();
    }

    @Test
    void setPageNumber() {
        assertThat(new PageImpl<>(Collections.emptyList(), -1, 10, 100).getPageNumber()).isEqualTo(1);
        assertThat(new PageImpl<>(Collections.emptyList(), 0, 10, 100).getPageNumber()).isEqualTo(1);
        assertThat(new PageImpl<>(Collections.emptyList(), 1, 10, 100).getPageNumber()).isEqualTo(1);
        assertThat(new PageImpl<>(Collections.emptyList(), 10, 10, 100).getPageNumber()).isEqualTo(10);
    }

    @Test
    void setPageSize() {
        assertThat(new PageImpl<>(Collections.emptyList(), 1, -1, 100).getPageSize()).isEqualTo(0);
        assertThat(new PageImpl<>(Collections.emptyList(), 1, 0, 100).getPageSize()).isEqualTo(0);
        assertThat(new PageImpl<>(Collections.emptyList(), 1, 10, 100).getPageSize()).isEqualTo(10);
    }

    @Test
    void setTotalRecords() {
        assertThat(new PageImpl<>(Collections.emptyList(), 1, 10, -100).getTotalRecords()).isEqualTo(0);
        assertThat(new PageImpl<>(Collections.emptyList(), 1, 10, 0).getTotalRecords()).isEqualTo(0);
        assertThat(new PageImpl<>(Collections.emptyList(), 1, 10, 100).getTotalRecords()).isEqualTo(100);
    }

    @Test
    public void getTotalPages() {
        assertThat(new PageImpl<>(Collections.emptyList(), 0, 0, 0).getTotalPages()).isEqualTo(0);
        assertThat(new PageImpl<>(Collections.emptyList(), -1, 0, 0).getTotalPages()).isEqualTo(0);
        assertThat(new PageImpl<>(Collections.emptyList(), 1, 10, 100).getTotalPages()).isEqualTo(10);
        assertThat(new PageImpl<>(Collections.emptyList(), 1, 10, 95).getTotalPages()).isEqualTo(10);
        assertThat(new PageImpl<>(Collections.emptyList(), 1, 10, 0).getTotalPages()).isEqualTo(0);
        assertThat(new PageImpl<>(Collections.emptyList(), 1, -10, -100).getTotalPages()).isEqualTo(0);
        assertThat(new PageImpl<>(Collections.emptyList(), 1, 10, -100).getTotalPages()).isEqualTo(0);
        assertThat(new PageImpl<>(Collections.emptyList(), 1, -10, 100).getTotalPages()).isEqualTo(0);
    }
}
