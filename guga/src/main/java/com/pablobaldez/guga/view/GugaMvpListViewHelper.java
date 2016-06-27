package com.pablobaldez.guga.view;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.trello.navi.Event;
import com.trello.navi.rx.RxNavi;

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
                .subscribe(bundle -> {});
    }

    public void findViews(View view) {

    }

    @Nullable
    public View getEmptyView() {
        return emptyView;
    }

    @Nullable
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Nullable
    public RecyclerView.Adapter<VH> getAdapter() {
        return adapter;
    }
}
