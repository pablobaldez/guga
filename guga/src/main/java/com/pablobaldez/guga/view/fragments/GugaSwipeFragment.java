package com.pablobaldez.guga.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pablobaldez.guga.R;
import com.pablobaldez.guga.view.wrappers.GugaSwipeWrapper;

/**
 * Fragment with list and swipe to refresh
 * @author pablobaldez
 * @since 7/11/16
 */
public abstract class GugaSwipeFragment<VH extends RecyclerView.ViewHolder>
        extends GugaRecyclerFragment<VH>
        implements SwipeRefreshLayout.OnRefreshListener {

    private static final int[] DEFAULT_SCHEME_COLORS =
            new int[]{R.color.swipe_refresh_color_1, R.color.swipe_refresh_color_2,
                    R.color.swipe_refresh_color_3, R.color.swipe_refresh_color_4};


    public SwipeRefreshLayout swipeRefreshLayout;
    private GugaSwipeWrapper swipeWrapper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.guga_swipe_recycler_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout = findSwipeRefreshLayout(view);
        setUpSwipe();
    }

    public void setUpSwipe() {
        swipeWrapper = new GugaSwipeWrapper(this, recyclerView, emptyView, swipeRefreshLayout);
        swipeWrapper.setUpSwipe(this, DEFAULT_SCHEME_COLORS);
    }

    @Override
    public void setLoadingState(boolean loadingState) {
        super.setLoadingState(loadingState);
        swipeWrapper.setLoadingState(loadingState);
    }

    @NonNull
    public SwipeRefreshLayout findSwipeRefreshLayout(View view) {
        return (SwipeRefreshLayout) view.findViewById(R.id.guga_swipe_refresh_layout);
    }
}
