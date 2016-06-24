package com.pablobaldez.guga.subscribers;

import com.pablobaldez.guga.GugaListMvpView;

import rx.functions.Action1;

/**
 * @author pablobaldez
 * @since 6/24/16
 */
public class GugaRemoveSubscriber<T> extends GugaListViewSubscriber<T> {

    public GugaRemoveSubscriber(GugaListMvpView view) {
        super(view);
    }

    public GugaRemoveSubscriber(GugaListMvpView view, Action1<T> onNextAction) {
        super(view, onNextAction);
    }

    public GugaRemoveSubscriber(GugaListMvpView view, Action1<T> onNextAction, int initialPosition) {
        super(view, onNextAction, initialPosition);
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        getView().notifyDataRemoved(getInitialPosition(), getOnNextCount());
    }
}
