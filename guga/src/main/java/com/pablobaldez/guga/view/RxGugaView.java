package com.pablobaldez.guga.view;


import rx.Observable;
import rx.android.MainThreadSubscription;

/**
 * @author Pablo
 * @since 27/05/2016
 */
public class RxGugaView {

    public static Observable<Boolean> setLoading(GugaViewDelegate delegate) {
        return Observable.create((Observable.OnSubscribe<Boolean>) subscriber -> {
            final SetLoadingStateListener listener = subscriber::onNext;
            delegate.addSetLoadingStateListener(listener);

            subscriber.add(new MainThreadSubscription() {
                @Override
                protected void onUnsubscribe() {
                    delegate.removeSetLoadingStateListener(listener);
                }
            });
        });
    }
}
