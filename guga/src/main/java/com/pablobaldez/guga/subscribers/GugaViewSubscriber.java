package com.pablobaldez.guga.subscribers;

import com.pablobaldez.guga.view.GugaMvpView;

import rx.Subscriber;
import rx.functions.Action1;

/**
 * Handle the loading state of the view and eventual errors
 * @author pablobaldez
 * @since 6/24/16
 */
public class GugaViewSubscriber<T> extends Subscriber<T> {

    private final GugaMvpView view;
    private final Action1<T> onNextAction;

    public GugaViewSubscriber(GugaMvpView view) {
        this(view, t -> {/*do nothing*/});
    }

    public GugaViewSubscriber(GugaMvpView view, Action1<T> onNextAction) {
        this.view = view;
        this.onNextAction = onNextAction;
    }

    @Override
    public void onStart() {
        super.onStart();
        view.setLoadingState(true);
    }

    @Override
    public void onCompleted() {
        view.setLoadingState(false);
    }

    @Override
    public void onError(Throwable e) {
        view.setLoadingState(false);
        view.showGenericErrorMessage();
    }

    @Override
    public void onNext(T t) {
        onNextAction.call(t);
    }

    public GugaMvpView getView() {
        return view;
    }
}
