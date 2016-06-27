package com.pablobaldez.guga.presenter;

import com.pablobaldez.guga.utils.ListActionSubscriber;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by pablobaldez on 5/12/16.
 * Notify the view following the subscriber events
 * @param <T> Type of emitted items
 */
public class GugaCollectionViewNotifier<T> extends GugaViewNotifier<T> {

    public static final int INITIAL_ARRAY_SIZE = 12;

    public final GugaListMvpView view;
    public int initialArraySize;


    public GugaCollectionViewNotifier(GugaListMvpView view){
        this(view, INITIAL_ARRAY_SIZE);
    }

    public GugaCollectionViewNotifier(GugaListMvpView view, int initialArraySize){
        super(view);
        this.view = view;
        this.initialArraySize = initialArraySize;
    }

    /**
     * Create a subscriber to refresh the data set when subscribed
     * @return Subscriber that will execute the predefined action
     */
    public ListActionSubscriber<T> subscriberToRefreshDataSet() {
        return subscriberToRefreshDataSet(list -> {/*do nothing*/});
    }

    /**
     * Create a subscriber to refresh the data set when subscribed
     * @param onLoadAll called when all items are loaded and before to notify the view
     * @return Subscriber that will execute the predefined action
     */
    public ListActionSubscriber<T> subscriberToRefreshDataSet(Action1<List<T>> onLoadAll) {
        ArrayList<T> dataSet = new ArrayList<>(initialArraySize);
        return super.subscriberToChangeLoadState(dataSet::add)
                .addOnCompletedAction(() -> onLoadAll.call(dataSet))
                .addOnCompletedAction(() -> view.notifyDataSetRefreshed(dataSet.size()));
    }

    /**
     * Create a subscriber to insert data into the data set when subscribed
     * @param initialPosition initial position to insert data
     * @param onLoadAll called when all items are loaded and before to notify the view
     * @return Subscriber that will execute the predefined action
     */
    public ListActionSubscriber<T> subscriberToInsertData(int initialPosition, Action1<Collection<T>> onLoadAll) {
        LinkedList<T> dataSet = new LinkedList<>();
        return super.subscriberToChangeLoadState(dataSet::add)
                .addOnCompletedAction(() -> onLoadAll.call(dataSet))
                .addOnCompletedAction(() -> view.notifyDataInserted(initialPosition, dataSet.size()));
    }

    /**
     * Create a subscriber to insert data into the data set when subscribed
     * @param onLoadAll called when all items are loaded and before to notify the view
     * @return Subscriber that will execute the predefined action
     */
    public ListActionSubscriber<T> subscriberToInsertData(Action1<Collection<T>> onLoadAll) {
        LinkedList<T> dataSet = new LinkedList<>();
        return super.subscriberToChangeLoadState(dataSet::add)
                .addOnCompletedAction(() -> onLoadAll.call(dataSet))
                .addOnCompletedAction(() -> view.notifyDataInserted(dataSet.size()));
    }

    /**
     * Create a subscriber to change data when subscribed
     * @param initialPosition initial position to change data
     * @param onChangeItem called when all items are changed and before to notify the view
     * @return Subscriber that will execute the predefined action
     */
    public ListActionSubscriber<T> subscriberToChangeData(int initialPosition, Action1<T> onChangeItem) {
        LinkedList<T> changes = new LinkedList<>();

        return super.subscriberToChangeLoadState(onChangeItem::call)
                .addOnNextAction(changes::add)
                .addOnCompletedAction(() -> view.notifyDataChanged(initialPosition, changes.size()));
    }

    /**
     * Create a subscriber to remove data from the data set when subscribed
     * @param initialPosition initial position to insert data
     * @return Subscriber that will execute the predefined action
     */
    public ListActionSubscriber<T> subscriberToRemoveData(int initialPosition) {
        LinkedList<T> removed = new LinkedList<>();
        return subscriberToRemoveData(initialPosition, list -> {/*do nothing*/});
    }

    /**
     * Create a subscriber to remove data from the data set when subscribed
     * @param initialPosition initial position to insert data
     * @param onRemoveItem called when all items are removed and before to notify the view
     * @return Subscriber that will execute the predefined action
     */
    public ListActionSubscriber<T> subscriberToRemoveData(int initialPosition, Action1<T> onRemoveItem) {
        LinkedList<T> removed = new LinkedList<>();
        return subscriberToChangeLoadState(onRemoveItem::call)
                .addOnNextAction(removed::add)
                .addOnCompletedAction(() -> view.notifyDataRemoved(initialPosition, removed.size()));
    }

    /**
     * Create a subscriber to move one item when subscribed
     * @param positionFrom initial item position
     * @param positionTo new item position
     * @return Subscriber that will execute the predefined action
     */
    public ListActionSubscriber<T> subscriberToMoveData(int positionFrom, int positionTo, Action1<T> onMoveItem) {
        return super.subscriberToChangeLoadState()
                .addOnNextAction(onMoveItem::call)
                .addOnCompletedAction(() -> view.notifyDataMoved(positionFrom, positionTo));
    }
}
