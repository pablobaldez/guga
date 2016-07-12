package com.pablobaldez.guga.subscribers;

import com.pablobaldez.guga.view.GugaListMvpView;

import java.util.List;

import rx.functions.Action1;

/**
 * Use this class to change a range of items in view
 * @author pablobaldez
 * @since 6/24/16
 */
public class GugaChangeItemSubscriber<T> extends GugaListViewSubscriber<T> {

    public GugaChangeItemSubscriber(GugaListMvpView view, Action1<List<T>> onCompleteList) {
        super(view, onCompleteList);
    }

    public GugaChangeItemSubscriber(GugaListMvpView view, Action1<List<T>> onCompleteList, int initialPosition) {
        super(view, onCompleteList, initialPosition);
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        getView().notifyDataChanged(getInitialPosition(), getList().size());
    }
}
