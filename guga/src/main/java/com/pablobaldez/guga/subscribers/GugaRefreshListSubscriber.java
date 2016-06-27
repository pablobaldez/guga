package com.pablobaldez.guga.subscribers;

import com.pablobaldez.guga.view.GugaListMvpView;

import rx.functions.Action1;

/**
 * @author pablobaldez
 * @since 6/24/16
 */
public class GugaRefreshListSubscriber<T> extends GugaListViewSubscriber<T> {

    public GugaRefreshListSubscriber(GugaListMvpView view) {
        super(view);
    }

    public GugaRefreshListSubscriber(GugaListMvpView view, Action1<T> onNextAction) {
        super(view, onNextAction);
    }

    public GugaRefreshListSubscriber(GugaListMvpView view, Action1<T> onNextAction, int initialPosition) {
        super(view, onNextAction, initialPosition);
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        getView().notifyDataSetRefreshed(getOnNextCount());
    }
}
