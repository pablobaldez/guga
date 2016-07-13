package com.pablobaldez.guga.view.activities;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.pablobaldez.guga.R;
import com.pablobaldez.guga.view.GugaDelegatedAdapter;
import com.pablobaldez.guga.view.GugaListMvpView;
import com.pablobaldez.guga.view.PaginationManager;
import com.pablobaldez.guga.view.wrappers.GugaRecyclerViewWrapper;

/**
 * @author pablobaldez
 * @since 7/12/16
 */
public abstract class GugaRefreshCompatActivity<VH extends RecyclerView.ViewHolder>
        extends GugaToolbarActivity
        implements GugaListMvpView, GugaDelegatedAdapter.Delegate<VH> {

    public RecyclerView recyclerView;
    @Nullable
    public View emptyView;

    private GugaRecyclerViewWrapper viewWrapper;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter<VH> adapter;
    private int itemCount = 0;
    private boolean moreItemsToLoad = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        adapter = createAdapter();
        layoutManager = createLayoutManager();
    }

    @NonNull
    public RecyclerView.Adapter<VH> createAdapter() {
        return new GugaDelegatedAdapter(this);
    }

    @NonNull
    public RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        recyclerView = findRecyclerView();
        emptyView = findEmptyView();
        setUpRecyclerView();
    }

    /**
     * Override it if you want to change the id of recycler view
     * @return RecyclerView used in this list
     */
    @NonNull
    public RecyclerView findRecyclerView() {
        return (RecyclerView) findViewById(R.id.guga_recycler_view);
    }

    /**
     * Override it if you want to change the id of the view to show when the list is empty
     * @return The view to be the empty layout
     */
    @Nullable
    public View findEmptyView() {
        return findViewById(R.id.guga_empty_view);
    }

    public void setUpRecyclerView() {
        viewWrapper = new GugaRecyclerViewWrapper(this, recyclerView, emptyView);
        viewWrapper.setUpRecyclerView(layoutManager, adapter);
    }

    public void setPaginationManager(PaginationManager paginationManager) {
        if (viewWrapper != null) {
            viewWrapper.setPaginationManager(paginationManager);
        }
    }

    public void setEmptyViewVisible(boolean toVisible){
        if (viewWrapper != null) {
            viewWrapper.setEmptyViewVisible(toVisible);
        }
    }

    @Override
    public void notifyDataSetRefreshed(int itemCount) {
        this.itemCount = itemCount;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void notifyDataChanged(int initialPosition, int itemCount) {
        adapter.notifyItemRangeChanged(initialPosition, itemCount);
    }

    @Override
    public void notifyDataInserted(int initialPosition, int itemCount) {
        adapter.notifyItemRangeInserted(initialPosition, itemCount);
    }

    @Override
    public void notifyDataInserted(int itemCount) {
        adapter.notifyItemRangeInserted(adapter.getItemCount(), itemCount);
    }

    @Override
    public void notifyDataRemoved(int initialPosition, int itemCount) {
        adapter.notifyItemRangeRemoved(initialPosition, itemCount);
    }

    @Override
    public void notifyDataMoved(int fromPosition, int toPosition) {
        adapter.notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public boolean hasMoreItemsToLoad() {
        return moreItemsToLoad;
    }

    @Override
    public void setMoreItemsToLoad(boolean moreItemsToLoad) {
        this.moreItemsToLoad = moreItemsToLoad;
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @LayoutRes
    public int getLayoutId() {
        return R.layout.guga_recycler_view;
    }
}
