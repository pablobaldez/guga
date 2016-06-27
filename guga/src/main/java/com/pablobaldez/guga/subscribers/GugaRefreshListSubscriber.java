package com.pablobaldez.guga.subscribers;

import com.pablobaldez.guga.view.GugaListMvpView;

import java.util.List;

import rx.functions.Action1;

/**
 * @author pablobaldez
 * @since 6/24/16
 */
public class GugaRefreshListSubscriber<T> extends GugaListViewSubscriber<T> {

    public GugaRefreshListSubscriber(GugaListMvpView view, Action1<List<T>> onCompleteList) {
        super(view, onCompleteList);
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        getView().notifyDataSetRefreshed(getList().size());
    }
}
