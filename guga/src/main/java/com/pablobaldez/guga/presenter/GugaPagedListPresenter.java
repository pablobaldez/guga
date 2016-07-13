package com.pablobaldez.guga.presenter;

import com.pablobaldez.guga.subscribers.GugaPaginationSubscriber;
import com.pablobaldez.guga.subscribers.GugaRefreshListSubscriber;
import com.pablobaldez.guga.view.GugaListMvpView;
import com.pablobaldez.guga.view.PaginationManager;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

import static com.pablobaldez.guga.utils.RxUtils.saveMainThreadIntoLifecycle;

/**
 * Created by pablobaldez on 5/12/16.
 * @param <T> list of what
 */
public abstract class GugaPagedListPresenter<T> implements PaginationManager {

    public static int defaultOffset;

    private final GugaListMvpView view;
    protected List<T> list;

    private int page = 0;
    private int offset;

    protected GugaPagedListPresenter(GugaListMvpView view) {
        this.view = view;
        this.list = new ArrayList<>();
        this.offset = defaultOffset;
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
    public void applyPagination() {
        page++;
        Subscriber<T> subscriber = new GugaPaginationSubscriber<>(view, list::addAll);
        saveMainThreadIntoLifecycle(fillData(), view)
                .subscribe(subscriber);
    }

    public int getPage() {
        return page;
    }

    @Override
    public int getOffset() {
        return offset;
    }

    @Override
    public void setOffset(int offset) {
        this.offset = offset;
    }

    public abstract Observable<T> fillData();

}
