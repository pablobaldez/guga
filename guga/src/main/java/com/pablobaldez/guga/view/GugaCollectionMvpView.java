package com.pablobaldez.guga.view;

/**
 * Created by pablobaldez on 5/10/16.
 * View that handles a list
 */
public interface GugaCollectionMvpView extends GugaMvpView {

    /**
     * Called when the current data set was cleaned and a new one was refreshed
     * @param itemCount number of items in this new data set
     */
    void notifyDataSetRefreshed(int itemCount);

    /**
     * Called when some data was changed
     * @param initialPosition initial position of changes
     * @param itemCount number of items changed
     */
    void notifyDataChanged(int initialPosition, int itemCount);

    /**
     * Called when some data was inserted in data set
     * @param initialPosition initial position of insertions
     * @param itemCount number of items inserted
     */
    void notifyDataInserted(int initialPosition, int itemCount);

    /**
     * Called when some data was inserted in data set
     * @param itemCount number of items inserted
     */
    void notifyDataInserted(int itemCount);

    /**
     * Called when some data was removed from data set
     * @param initialPosition initial position of removes
     * @param itemCount number of items removed
     */
    void notifyDataRemoved(int initialPosition, int itemCount);

    /**
     * Called when one item was moved to a new position
     * @param fromPosition initial item position
     * @param toPosition current item position
     */
    void notifyDataMoved(int fromPosition, int toPosition);
}
