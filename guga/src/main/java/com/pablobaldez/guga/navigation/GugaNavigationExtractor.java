package com.pablobaldez.guga.navigation;

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
public abstract class GugaNavigationExtractor<T> implements NavigationForResult<T>{

    private final GugaMvpView view;

    public GugaNavigationExtractor(GugaMvpView view) {
        this.view = view;
    }

    @Override
    public Observable<T> extract() {
        return RxNavi.observe(view, Event.ACTIVITY_RESULT)
                .map(this::mapData);
    }

    @Nullable
    public abstract T mapData(ActivityResult activityResult);
}
