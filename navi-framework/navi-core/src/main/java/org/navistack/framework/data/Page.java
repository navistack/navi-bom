package org.navistack.framework.data;

import java.util.Collection;

public interface Page<T> {
    /**
     * Returns the number of the current {@link Page}. Is always non-negative.
     *
     * @return the number of the current {@link Page}.
     */
    int getPageNumber();

    /**
     * Returns the size of the {@link Page}.
     *
     * @return the size of the {@link Page}.
     */
    int getPageSize();

    /**
     * Returns the number of total pages.
     *
     * @return the number of total pages
     */
    long getTotalPages();

    /**
     * Returns the page records as {@link Collection}.
     *
     * @return {@link Collection} containing the pae records
     */
    Collection<T> getRecords();

    /**
     * Returns the current amount of records.
     *
     * @return the current amount of records
     */
    int getCurrentRecords();

    /**
     * Returns the total amount of records.
     *
     * @return the total amount of records
     */
    long getTotalRecords();

}
