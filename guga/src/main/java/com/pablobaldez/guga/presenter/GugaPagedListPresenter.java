package com.pablobaldez.guga.presenter;

import com.pablobaldez.guga.subscribers.GugaSubscribers;
import com.pablobaldez.guga.view.GugaListMvpView;
import com.pablobaldez.guga.view.PaginationManager;

import java.util.ArrayList;

import rx.Subscriber;

import static com.pablobaldez.guga.utils.RxUtils.saveMainThreadIntoLifecycle;

/**
 * Presenter to
 * Created by pablobaldez on 5/12/16.
 * @param <T> list of what
 */
public abstract class GugaPagedListPresenter<T> extends GugaListPresenter<T> implements PaginationManager {

    public static int defaultOffset;

    private int page = 0;
    private int offset;

    public GugaPagedListPresenter(GugaListMvpView view) {
        super(view);
        this.list = new ArrayList<>();
        this.offset = defaultOffset;
    }

    @Override
    public void refreshView() {
        page = 0;
        super.refreshView();
    }

    @Override
    public void applyPagination() {
        page++;
        final GugaListMvpView view = getView();
        final Subscriber<T> subscriber = GugaSubscribers.toPagination(view, list::addAll);
        saveMainThreadIntoLifecycle(loadData(), view).subscribe(subscriber);
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


}
