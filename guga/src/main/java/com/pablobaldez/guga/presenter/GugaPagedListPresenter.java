package com.pablobaldez.guga.presenter;

import com.pablobaldez.guga.view.GugaCollectionMvpView;
import com.pablobaldez.guga.view.PagedDataProvider;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import static com.pablobaldez.guga.utils.RxUtils.saveMainThreadIntoLifecycle;

/**
 * Created by pablobaldez on 5/12/16.
 * @param <T> list of what
 */
public abstract class GugaPagedListPresenter<T> implements PagedDataProvider {

    private final GugaListMvpView view;
    protected final GugaCollectionViewNotifier<T> notifier;
    protected List<T> list;

    private int page = 0;

    protected GugaPagedListPresenter(GugaListMvpView view) {
        this(view, new GugaCollectionViewNotifier<>(view));
    }

    protected GugaPagedListPresenter(GugaListMvpView view, GugaCollectionViewNotifier<T> notifier) {
        this.view = view;
        this.notifier = notifier;
        this.list = new ArrayList<>();
    }

    /**
     * Reset pagination and load data
     */
    public void refreshData() {
        page = 0;
        saveMainThreadIntoLifecycle(fillData(), view)
                .subscribe(notifier.subscriberToRefreshDataSet(list -> this.list = list));
    }

    @Override
    public void loadMore() {
        page++;
        saveMainThreadIntoLifecycle(fillData(), view)
                .subscribe(notifier.subscriberToInsertData(list::addAll));
    }

    public int getPage() {
        return page;
    }

    public abstract Observable<T> fillData();

}
