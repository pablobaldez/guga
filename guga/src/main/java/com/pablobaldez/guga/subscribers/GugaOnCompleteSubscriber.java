package com.pablobaldez.guga.subscribers;

import rx.Subscriber;

/**
 * @author pablobaldez
 * @since 7/18/16
 */
public abstract class GugaOnCompleteSubscriber<T> extends Subscriber<T> {

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }
}
