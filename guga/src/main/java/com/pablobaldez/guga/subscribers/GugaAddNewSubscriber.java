package com.pablobaldez.guga.subscribers;

import com.pablobaldez.guga.view.GugaListMvpView;

import java.util.List;

import rx.functions.Action1;

/**
 * Add new items at top of list
 * @author pablobaldez
 * @since 7/13/16
 */
public class GugaAddNewSubscriber<T> extends GugaListViewSubscriber<T> {


    public GugaAddNewSubscriber(GugaListMvpView view, Action1<List<T>> onCompleteList) {
        super(view, onCompleteList);
    }

    public GugaAddNewSubscriber(GugaListMvpView view, Action1<List<T>> onCompleteList, int initialPosition) {
        super(view, onCompleteList, initialPosition);
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        int news = getList().size();
        getView().notifyDataInserted(getInitialPosition(), news);
    }
}
