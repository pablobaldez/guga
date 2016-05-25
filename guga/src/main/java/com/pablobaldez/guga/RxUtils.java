package com.pablobaldez.guga;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pablobaldez on 5/12/16.
 */
public final class RxUtils {

    public static <U> Observable<U> saveMainThread(Observable<U> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private RxUtils() {

    }

}
