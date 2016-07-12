package com.pablobaldez.guga.view.wrappers;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pablobaldez.guga.R;
import com.pablobaldez.guga.view.GugaListMvpView;

/**
 * @author pablobaldez
 * @since 7/12/16
 */
public class GugaSwipeWrapper extends GugaRecyclerViewWrapper {

    public static final int[] DEFAULT_SCHEME_COLORS =
            new int[]{R.color.swipe_refresh_color_1, R.color.swipe_refresh_color_2,
                    R.color.swipe_refresh_color_3, R.color.swipe_refresh_color_4};

    private SwipeRefreshLayout swipeRefreshLayout;

    public GugaSwipeWrapper(GugaListMvpView view, RecyclerView recyclerView, View emptyView, SwipeRefreshLayout swipeRefreshLayout) {
        super(view, recyclerView, emptyView);
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    public void setUpSwipe(SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        swipeRefreshLayout.setColorSchemeColors(DEFAULT_SCHEME_COLORS);
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
