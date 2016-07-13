package com.pablobaldez.guga.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pablobaldez.guga.R;
import com.pablobaldez.guga.view.GugaDelegatedAdapter;
import com.pablobaldez.guga.view.GugaListMvpView;
import com.pablobaldez.guga.view.PaginationManager;
import com.pablobaldez.guga.view.wrappers.GugaRecyclerViewWrapper;

/**
 * Created by pablobaldez on 5/12/16.
 * Fragment that shows a list to user using recyclerview 
 */
public abstract class GugaRecyclerFragment<VH extends RecyclerView.ViewHolder>
        extends GugaFragment
        implements GugaListMvpView, GugaDelegatedAdapter.Delegate<VH> {

    public RecyclerView recyclerView;
    @Nullable  public View emptyView;

    private GugaRecyclerViewWrapper viewWrapper;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter<VH> adapter;
    private int itemCount = 0;
    private boolean moreItemsToLoad = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = createAdapter();
        layoutManager = createLayoutManager();
    }

    @NonNull
    public RecyclerView.Adapter<VH> createAdapter() {
        return new GugaDelegatedAdapter(this);
    }

    @NonNull
    public RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.guga_recycler_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = findRecyclerView(view);
        emptyView = findEmptyView(view);
        setUpRecyclerView();
    }

    /**
     * Override it if you want to change the id of recycler view
     * @param view view host defined in #onViewCreated
     * @return RecyclerView used in this list
     */
    @NonNull
    public RecyclerView findRecyclerView(View view) {
        return (RecyclerView) view.findViewById(R.id.guga_recycler_view);
    }

    /**
     * Override it if you want to change the id of the view to show when the list is empty
     * @param view view view host defined in #onViewCreated
     * @return The view to be the empty layout
     */
    @Nullable
    public View findEmptyView(View view) {
        return view.findViewById(R.id.guga_empty_view);
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

}
