package org.navistack.framework.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class PageImpl<T> implements Page<T> {
    private Collection<T> records;
    private int pageNumber;
    private int pageSize;
    private long totalRecords;

    @Override
    public int getTotalPages() {
        long totalRecords = getTotalRecords();
        int pageSize = getPageSize();

        return pageSize <= 0
                ? 0
                : (int) Math.ceil((double) totalRecords / pageSize);
    }
}
