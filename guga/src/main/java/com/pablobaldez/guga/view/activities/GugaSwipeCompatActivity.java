package com.pablobaldez.guga.view.activities;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.pablobaldez.guga.R;
import com.pablobaldez.guga.view.wrappers.GugaSwipeWrapper;

/**
 * @author pablobaldez
 * @since 7/12/16
 */
public abstract class GugaSwipeCompatActivity<VH extends RecyclerView.ViewHolder>
        extends GugaRefreshCompatActivity<VH>
        implements SwipeRefreshLayout.OnRefreshListener{

    public SwipeRefreshLayout swipeRefreshLayout;
    private GugaSwipeWrapper swipeWrapper;

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        swipeRefreshLayout = findSwipeRefreshLayout();
        setUpSwipe();
    }

    public void setUpSwipe() {
        swipeWrapper = new GugaSwipeWrapper(this, recyclerView, emptyView, swipeRefreshLayout);
        swipeWrapper.setUpSwipe(this);
    }

    @Override
    public void setLoadingState(boolean loadingState) {
        super.setLoadingState(loadingState);
        swipeWrapper.setLoadingState(loadingState);
    }

    @NonNull
    public SwipeRefreshLayout findSwipeRefreshLayout() {
        return (SwipeRefreshLayout) findViewById(R.id.guga_swipe_refresh_layout);
    }

    @Override
    public int getLayoutId() {
        return R.layout.guga_swipe_recycler_view;
    }
}
