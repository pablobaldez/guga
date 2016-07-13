package com.pablobaldez.guga.presenter;

import com.pablobaldez.guga.subscribers.GugaSubscribers;
import com.pablobaldez.guga.view.GugaListMvpView;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

import static com.pablobaldez.guga.utils.RxUtils.saveMainThreadIntoLifecycle;

/**
 * The base subscriber for lists
 * @author pablobaldez
 * @since 7/13/16
 * <T> list of what
 */
public abstract class GugaListPresenter<T> {

    private final GugaListMvpView view;

    protected List<T> list;

    public GugaListPresenter(GugaListMvpView view) {
        this.view = view;
    }

    public void refreshView() {
        final Subscriber<T> subscriber = GugaSubscribers.toRefresh(view, newList -> list = newList);
        saveMainThreadIntoLifecycle(loadData(), view).subscribe(subscriber);
    }

    public T get(int position) {
        return list.get(position);
    }

    public GugaListMvpView getView() {
        return view;
    }

    /**
     * Load the data from some source to populate the list of view. Here you can interact with
     * use cases, interactors, data sources, files, etc.
     *
     * @return Observable that when subscriber will emit the items.
     */
    public abstract Observable<T> loadData();
}
