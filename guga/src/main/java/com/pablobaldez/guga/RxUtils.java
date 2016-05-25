package com.pablobaldez.guga;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pablobaldez on 5/12/16.
 */
public final class RxUtils {

    public static <T> Observable<T> saveMainThread(Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Observable<T> saveMainThreadIntoLifecycle(Observable<T> observable, GugaMvpView view){
        return saveMainThread(view.bindIntoLifecycle(observable));
    }

    private RxUtils() {

    }

}
