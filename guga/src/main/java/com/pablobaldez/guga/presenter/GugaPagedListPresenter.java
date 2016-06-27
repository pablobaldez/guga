package com.pablobaldez.guga.presenter;

import com.pablobaldez.guga.subscribers.GugaAddMoreSubscriber;
import com.pablobaldez.guga.subscribers.GugaRefreshListSubscriber;
import com.pablobaldez.guga.view.GugaListMvpView;
import com.pablobaldez.guga.view.PagedDataProvider;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

import static com.pablobaldez.guga.utils.RxUtils.saveMainThreadIntoLifecycle;

/**
 * Created by pablobaldez on 5/12/16.
 * @param <T> list of what
 */
public abstract class GugaPagedListPresenter<T> implements PagedDataProvider {

    private final GugaListMvpView view;
    protected List<T> list;

    private int page = 0;

    protected GugaPagedListPresenter(GugaListMvpView view) {
        this.view = view;
        this.list = new ArrayList<>();
    }

    /**
     * Reset pagination and load data
     */
    public void refreshData() {
        page = 0;
        Subscriber<T> subscriber = new GugaRefreshListSubscriber<>(view, newList -> this.list = newList);
        saveMainThreadIntoLifecycle(fillData(), view)
                .subscribe(subscriber);
    }

    @Override
    public void loadMore() {
        page++;
        Subscriber<T> subscriber = new GugaAddMoreSubscriber<>(view, list::addAll);
        saveMainThreadIntoLifecycle(fillData(), view)
                .subscribe(subscriber);
    }

    public int getPage() {
        return page;
    }

    public abstract Observable<T> fillData();

}
