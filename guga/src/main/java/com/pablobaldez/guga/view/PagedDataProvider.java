package com.pablobaldez.guga.view;

/**
 * Created by pablobaldez on 5/12/16.
 * Load
 */
public interface PagedDataProvider {

    /**
     * Set the limit of items load per request
     * @param limit new limit
     */
    void setLimit(int limit);

    /**
     * Load more items
     */
    void loadMore();

}
