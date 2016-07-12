package com.pablobaldez.guga.view.wrappers;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerViewAdapter;
import com.mugen.MugenCallbacks;
import com.pablobaldez.guga.view.GugaListMvpView;
import com.pablobaldez.guga.view.GugaRecyclerViewAttacher;
import com.pablobaldez.guga.view.PaginationManager;
import com.trello.navi.Event;
import com.trello.navi.rx.RxNavi;


/**
 * Class to handle the views of a list view. Use it when you want to implement an GugaListMvpView
 * and reuse the same code for all clients
 * @author pablobaldez
 * @since 7/12/16
 */
public class GugaRecyclerViewWrapper implements MugenCallbacks{

    private final GugaListMvpView view;

    private RecyclerView recyclerView;
    private View emptyView;

    private GugaRecyclerViewAttacher attacher;
    private PaginationManager paginationManager;

    public GugaRecyclerViewWrapper(GugaListMvpView view, RecyclerView recyclerView, View emptyView) {
        this.view = view;
        this.recyclerView = recyclerView;
        this.emptyView = emptyView;
    }

    public void setUpRecyclerView(RecyclerView.LayoutManager layoutManager,
                                  RecyclerView.Adapter<?> adapter) {
        setUpRecyclerView(layoutManager, adapter, false);
    }

    public void setUpRecyclerView(RecyclerView.LayoutManager layoutManager,
                                  RecyclerView.Adapter<?> adapter,
                                  boolean hasFixedSize) {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(hasFixedSize);

        if (emptyView != null) {
            RxRecyclerViewAdapter
                    .dataChanges(adapter)
                    .takeUntil(RxNavi.observe(view, Event.STOP))
                    .subscribe(vh -> setEmptyViewVisible(adapter.getItemCount() == 0));
        }
    }

    public void setEmptyViewVisible(boolean toVisible){
        if (emptyView == null) {
            return;
        }
        if (toVisible) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }

    public void setPaginationManager(PaginationManager paginationManager) {
        this.paginationManager = paginationManager;
        if (paginationManager == null) {
            attacher.stop();
        }
        else {
            attacher.setLoadMoreOffset(paginationManager.getOffset());
            attacher.start();
        }
    }

    @Override
    public void onLoadMore() {
        if (paginationManager != null) {
            paginationManager.applyPagination();
        }
    }

    @Override
    public boolean isLoading() {
        return view.isLoadingState();
    }

    @Override
    public boolean hasLoadedAllItems() {
        return view.hasMoreItemsToLoad();
    }
}
