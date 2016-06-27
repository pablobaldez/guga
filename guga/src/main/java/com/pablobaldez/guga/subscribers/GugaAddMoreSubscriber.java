package com.pablobaldez.guga.subscribers;

import com.pablobaldez.guga.view.GugaListMvpView;

import java.util.List;

import rx.functions.Action1;

/**
 * @author pablobaldez
 * @since 6/24/16
 */
public class GugaAddMoreSubscriber<T> extends GugaListViewSubscriber<T> {


    public GugaAddMoreSubscriber(GugaListMvpView view, Action1<List<T>> onCompleteList) {
        super(view, onCompleteList);
    }

    public GugaAddMoreSubscriber(GugaListMvpView view, Action1<List<T>> onCompleteList, int initialPosition) {
        super(view, onCompleteList, initialPosition);
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        getView().notifyDataInserted(getInitialPosition(), getList().size());
    }
}
