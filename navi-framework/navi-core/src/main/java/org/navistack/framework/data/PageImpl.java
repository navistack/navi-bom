package org.navistack.framework.data;

import lombok.Data;

import java.util.Collection;
import java.util.Collections;

@Data
public class PageImpl<T> implements Page<T> {
    private Collection<T> records;
    private int pageNumber;
    private int pageSize;
    private long totalRecords;

    public PageImpl(Collection<T> records, int pageNumber, int pageSize, long totalRecords) {
        setRecords(records);
        setPageNumber(pageNumber);
        setPageSize(pageSize);
        setTotalRecords(totalRecords);
    }

    public PageImpl(Collection<T> records, Pageable pageable, long totalRecords) {
        setRecords(records);
        setPageNumber(pageable.getPageNumber());
        setPageSize(pageable.getPageSize());
        setTotalRecords(totalRecords);
    }

    public PageImpl(Collection<T> records) {
        setRecords(records);
        setPageNumber(1);
        setPageSize(records.size());
        setTotalRecords(records.size());
    }

    public void setRecords(Collection<T> records) {
        this.records = records != null ? records : Collections.emptyList();
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = Math.max(1, pageNumber);
    }

    public void setPageSize(int pageSize) {
        this.pageSize = Math.max(0, pageSize);
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = Math.max(0, totalRecords);
    }

    @Override
    public int getCurrentRecords() {
        return this.records != null ? this.records.size() : 0;
    }

    @Override
    public long getTotalPages() {
        long totalRecords = getTotalRecords();
        int pageSize = getPageSize();

        return pageSize < 1
                ? 0
                : (totalRecords + pageSize - 1) / pageSize;
    }
}
