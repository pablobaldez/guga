package com.pablobaldez.guga.view.wrappers;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pablobaldez.guga.view.GugaListMvpView;

/**
 * @author pablobaldez
 * @since 7/12/16
 */
public class GugaSwipeWrapper extends GugaRecyclerViewWrapper {

    private SwipeRefreshLayout swipeRefreshLayout;

    public GugaSwipeWrapper(GugaListMvpView view, RecyclerView recyclerView, View emptyView, SwipeRefreshLayout swipeRefreshLayout) {
        super(view, recyclerView, emptyView);
    }

    public void setUpSwipe(SwipeRefreshLayout.OnRefreshListener onRefreshListener, int... colorScheme) {
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        swipeRefreshLayout.setColorSchemeColors(colorScheme);
    }

    public void setLoadingState(boolean loadingState) {
        if(loadingState && !swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setEnabled(false);
        }
        else {
            swipeRefreshLayout.setEnabled(true);
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
