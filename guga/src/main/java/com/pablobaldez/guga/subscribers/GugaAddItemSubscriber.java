package com.pablobaldez.guga.subscribers;

import com.pablobaldez.guga.view.GugaListMvpView;

import java.util.List;

import rx.functions.Action1;

/**
 * Use this class to apply the pagination on view
 * @author pablobaldez
 * @since 6/24/16
 */
public class GugaAddItemSubscriber<T> extends GugaListViewSubscriber<T> {


    public GugaAddItemSubscriber(GugaListMvpView view, Action1<List<T>> onCompleteList) {
        super(view, onCompleteList);
    }

    public GugaAddItemSubscriber(GugaListMvpView view, Action1<List<T>> onCompleteList, int initialPosition) {
        super(view, onCompleteList, initialPosition);
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        GugaListMvpView view = getView();
        List<T> list = getList();
        view.notifyDataInserted(getInitialPosition(), list.size());
        view.setHasMoreItemsToLoad(list.isEmpty());
    }
}
