package com.pablobaldez.guga;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import static com.pablobaldez.guga.RxUtils.saveMainThreadIntoLifecycle;

/**
 * Created by pablobaldez on 5/12/16.
 * @param <T> list of what
 */
public abstract class GugaPagedListPresenter<T> implements PagedDataProvider {

    private final GugaCollectionMvpView view;
    protected final GugaCollectionViewNotifier<T> notifier;
    protected List<T> list;

    public int page = 0;

    protected GugaPagedListPresenter(GugaCollectionMvpView view) {
        this(view, new GugaCollectionViewNotifier<>(view));
    }

    protected GugaPagedListPresenter(GugaCollectionMvpView view, GugaCollectionViewNotifier<T> notifier) {
        this.view = view;
        this.notifier = notifier;
        this.list = new ArrayList<>();
    }

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

    public abstract Observable<T> fillData();

}
