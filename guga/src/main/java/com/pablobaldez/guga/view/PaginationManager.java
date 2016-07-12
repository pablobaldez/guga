package com.pablobaldez.guga.view;

/**
 * @author pablobaldez
 */
public interface PaginationManager {

    /**
     * Load more items to apply the pagination
     */
    void applyPagination();

    void setOffset(int offset);

    int getOffset();

}
