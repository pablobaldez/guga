package com.pablobaldez.guga.subscribers;

import com.pablobaldez.guga.view.GugaListMvpView;

import rx.functions.Action1;

/**
 * @author pablobaldez
 * @since 6/24/16
 */
public class GugaAddMoreSubscriber<T> extends GugaListViewSubscriber<T> {

    public GugaAddMoreSubscriber(GugaListMvpView view) {
        super(view);
    }

    public GugaAddMoreSubscriber(GugaListMvpView view, Action1<T> onNextAction) {
        super(view, onNextAction);
    }

    public GugaAddMoreSubscriber(GugaListMvpView view, Action1<T> onNextAction, int initialPosition) {
        super(view, onNextAction, initialPosition);
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        getView().notifyDataInserted(getInitialPosition(), getOnNextCount());
    }
}
