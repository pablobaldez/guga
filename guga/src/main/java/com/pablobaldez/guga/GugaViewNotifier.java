package com.pablobaldez.guga;

import rx.functions.Action0;
import rx.functions.Action1;

/**
 *
 * Created by pablobaldez on 5/10/16.
 * Notify the view following the subscriber events
 * @param <T> Type of emitted items
 */
public class GugaViewNotifier<T> {

    public final GugaMvpView view;

    public GugaViewNotifier(GugaMvpView view) {
        this.view = view;
    }

    /**
     * Create a subscriber to change the view load state
     * @param onNextAction action to be called when subscriber trigger onNext event
     * @return Subscriber to interact with view
     */
    public ListActionSubscriber<T> subscriberToChangeLoadState(Action1<T> onNextAction){
        return subscriberToChangeLoadState().addOnNextAction(onNextAction);
    }

    /**
     * Create a subscriber without onNext actions, that notifies the view loading state
     * @return Subscriber to interact with view
     */
    public ListActionSubscriber<T> subscriberToChangeLoadState() {
        return new ListActionSubscriber<T>()
                .addOnStartAction(onStartAction())
                .addOnCompletedAction(onCompletedAction())
                .addOnErrorAction(onErrorAction());
    }

    /**
     * @return Action that will be called when subscriber defined on {@link #subscriberToChangeLoadState()} calls
     * onStart
     */
    public Action0 onStartAction() {
        return () -> view.setLoadingState(true);
    }

    /**
     * @return Action that will be called when subscriber defined on {@link #subscriberToChangeLoadState()} calls
     * onCompleted
     */
    public Action0 onCompletedAction() {
        return () -> view.setLoadingState(false);
    }

    /**
     * @return Action that will be called when subscriber defined on {@link #subscriberToChangeLoadState()} calls
     * onError
     */
    public Action1<Throwable> onErrorAction() {
        return throwable -> {
            view.setLoadingState(false);
            view.showGenericErrorMessage();
        };
    }
}
