package com.pablobaldez.guga.presenter;

import android.support.annotation.Nullable;

import com.pablobaldez.guga.view.GugaMvpView;
import com.trello.navi.Event;
import com.trello.navi.model.ActivityResult;
import com.trello.navi.rx.RxNavi;

import rx.Observable;

/**
 * @author pablobaldez
 * @since 7/18/16
 */
public abstract class GugaNavigationExtractor<T> implements NavigationExtractor<T>{

    private final GugaMvpView view;
    private final String[] keys;

    public GugaNavigationExtractor(GugaMvpView view) {
        this.view = view;
        keys = new String[0];
    }

    public GugaNavigationExtractor(GugaMvpView view, String... keys) {
        this.view = view;
        this.keys = keys;
    }

    @Override
    public Observable<T> extract() {
        return RxNavi.observe(view, Event.ACTIVITY_RESULT)
                .map(this::mapData);
    }

    @Nullable
    public abstract T mapData(ActivityResult activityResult);
}
