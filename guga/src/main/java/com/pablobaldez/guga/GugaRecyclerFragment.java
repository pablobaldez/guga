package com.pablobaldez.guga;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by pablobaldez on 5/12/16.
 */
public abstract class GugaRecyclerFragment<VH extends RecyclerView.ViewHolder>
        extends GugaFragment
        implements GugaCollectionMvpView, GugaDelegatedAdapter.Delegate<VH> {

    private int itemCount = 0;
    private RecyclerView.Adapter<VH> adapter;

    public RecyclerView recyclerView;
    public ProgressBar progressBar;

    private View emptyView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = createAdapter();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
    public void notifyDataRemoved(int initialPosition, int itemCount) {
        adapter.notifyItemRangeRemoved(initialPosition, itemCount);
    }

    @Override
    public void notifyDataMoved(int fromPosition, int toPosition) {
        adapter.notifyItemMoved(fromPosition, toPosition);
    }

    public RecyclerView findRecyclerView() {
        return null;
    }

    public RecyclerView.Adapter<VH> createAdapter() {
        return new GugaDelegatedAdapter(this);
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
