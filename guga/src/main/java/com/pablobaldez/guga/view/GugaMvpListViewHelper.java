package com.pablobaldez.guga.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.trello.navi.Event;
import com.trello.navi.rx.RxNavi;
import com.trello.rxlifecycle.RxLifecycle;

/**
 * @author pablobaldez
 * @since 6/27/16
 */
public class GugaMvpListViewHelper<VH extends RecyclerView.ViewHolder> {

    private final GugaRecyclerFragment view;

    private RecyclerView.Adapter<VH> adapter;

    public RecyclerView recyclerView;
    private View emptyView;

    public GugaMvpListViewHelper(GugaRecyclerFragment view) {
        this.view = view;

        RxNavi.observe(view, Event.CREATE_VIEW)
                .
    }

    public View getEmptyView() {
        return emptyView;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public RecyclerView.Adapter<VH> getAdapter() {
        return adapter;
    }
}
