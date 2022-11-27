package org.navistack.framework.data;

public interface Pageable {
    /**
     * Returns the page to be returned.
     *
     */
    int getPageNumber();

    /**
     * Returns the number of items to be returned.
     *
     */
    int getPageSize();

    /**
     * Returns the offset to be taken according to the underlying page and page size.
     *
     */
    long getOffset();



    /**
     * Returns the sorting parameters.
     *
     */
    Sort getSort();
}
