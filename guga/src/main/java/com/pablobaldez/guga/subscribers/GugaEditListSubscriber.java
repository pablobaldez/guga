package com.pablobaldez.guga.subscribers;

import com.pablobaldez.guga.view.GugaListMvpView;

import rx.functions.Action1;

/**
 * @author pablobaldez
 * @since 6/24/16
 */
public class GugaEditListSubscriber<T> extends GugaListViewSubscriber<T> {


    public GugaEditListSubscriber(GugaListMvpView view) {
        super(view);
    }

    public GugaEditListSubscriber(GugaListMvpView view, Action1<T> onNextAction) {
        super(view, onNextAction);
    }

    public GugaEditListSubscriber(GugaListMvpView view, Action1<T> onNextAction, int initialPosition) {
        super(view, onNextAction, initialPosition);
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        getView().notifyDataChanged(getInitialPosition(), getOnNextCount());
    }
}
