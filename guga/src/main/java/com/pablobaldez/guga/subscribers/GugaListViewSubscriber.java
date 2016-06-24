package com.pablobaldez.guga.subscribers;

import com.pablobaldez.guga.GugaListMvpView;

import rx.functions.Action1;

/**
 * @author pablobaldez
 * @since 6/24/16
 */
public class GugaListViewSubscriber<T> extends GugaViewSubscriber<T> {

    private final int initialPosition;
    private final GugaListMvpView view;

    private int onNextCount;

    public GugaListViewSubscriber(GugaListMvpView view) {
        this(view, t -> {/*do nothing*/}, 0);
    }

    public GugaListViewSubscriber(GugaListMvpView view, Action1<T> onNextAction) {
        this(view, onNextAction, 0);
    }

    public GugaListViewSubscriber(GugaListMvpView view, Action1<T> onNextAction, int initialPosition) {
        super(view, onNextAction);
        this.view = view;
        this.initialPosition = initialPosition;
    }

    @Override
    public void onStart() {
        super.onStart();
        onNextCount = 0;
    }

    @Override
    public void onNext(T t) {
        super.onNext(t);
        onNextCount++;
    }

    public int getOnNextCount() {
        return onNextCount;
    }

    public int getInitialPosition() {
        return initialPosition;
    }

    @Override
    public GugaListMvpView getView() {
        return view;
    }
}
