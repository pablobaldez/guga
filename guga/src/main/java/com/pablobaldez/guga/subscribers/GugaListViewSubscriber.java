package com.pablobaldez.guga.subscribers;

import android.support.annotation.NonNull;

import com.pablobaldez.guga.view.GugaListMvpView;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * @author pablobaldez
 * @since 6/24/16
 */
public class GugaListViewSubscriber<T> extends GugaViewSubscriber<T> {

    private final int initialPosition;
    private final GugaListMvpView view;
    private final Action1<List<T>> onCompleteList;

    private final List<T> list = new ArrayList<>();

    public GugaListViewSubscriber(GugaListMvpView view,
                                  Action1<List<T>> onCompleteList) {
        this(view, onCompleteList, 0);
    }

    public GugaListViewSubscriber(GugaListMvpView view,
                                  Action1<List<T>> onCompleteList,
                                  int initialPosition) {
        super(view);
        this.view = view;
        this.onCompleteList = onCompleteList;
        this.initialPosition = initialPosition;
    }

    @Override
    public void onStart() {
        super.onStart();
        list.clear();
    }

    @Override
    public void onNext(T t) {
        super.onNext(t);
        list.add(t);
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        onCompleteList.call(list);
    }

    @Override
    @NonNull
    public GugaListMvpView getView() {
        return view;
    }

    @NonNull
    public List<T> getList() {
        return list;
    }

    /**
     * @return Initial position of list changes
     */
    public int getInitialPosition() {
        return initialPosition;
    }
}
