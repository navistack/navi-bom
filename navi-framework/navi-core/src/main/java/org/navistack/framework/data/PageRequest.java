package org.navistack.framework.data;

import lombok.Data;
import org.navistack.framework.core.utils.MathUtils;

@Data
public class PageRequest implements Pageable {
    private static int MIN_PAGE_NUMBER = 1;
    private static int MIN_PAGE_SIZE = 0;
    private static int MAX_PAGE_SIZE = 1000;

    private int pageNumber = MIN_PAGE_NUMBER;
    private int pageSize = MIN_PAGE_SIZE;
    private Sort sort = Sort.unsorted();

    public void setPageNumber(int pageNumber) {
        this.pageNumber = Math.max(pageNumber, MIN_PAGE_NUMBER);
    }

    public void setPageSize(int pageSize) {
        this.pageSize =  MathUtils.clamp(pageSize, MIN_PAGE_SIZE, MAX_PAGE_SIZE);
    }
}
