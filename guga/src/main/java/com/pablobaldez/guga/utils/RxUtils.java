package com.pablobaldez.guga.utils;

import com.pablobaldez.guga.view.GugaMvpView;
import com.trello.navi.Event;
import com.trello.navi.rx.RxNavi;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Utilities rx methods commons for all layers
 * Created by pablobaldez on 5/12/16
 */
public final class RxUtils {

    public static <T> Observable<T> saveMainThreadIntoLifecycle(Observable<T> observable, GugaMvpView view){
        return saveMainThread(observable.takeUntil(RxNavi.observe(view, Event.DESTROY)));
    }

    public static <T> Observable<T> saveMainThread(Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private RxUtils() {

    }

}
