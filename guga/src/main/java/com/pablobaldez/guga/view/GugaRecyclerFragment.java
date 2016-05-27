package com.pablobaldez.guga.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.jakewharton.rxbinding.support.v7.widget.RecyclerViewScrollEvent;
import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;
import com.pablobaldez.guga.R;

import rx.Subscriber;

/**
 * Created by pablobaldez on 5/12/16.
 * Fragment that shows a list to user using recyclerview 
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.guga_recycler_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = findRecyclerView(view);

    }

    //ADAPTER METHODS-------------------------------------------------------------------------------
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

    @Override
    public int getItemCount() {
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }


    //OWN METHODS-----------------------------------------------------------------------------------
    @NonNull
    public RecyclerView findRecyclerView(View view) {
        return (RecyclerView) view.findViewById(R.id.guga_recycler_view);
    }

    @Nullable
    public View findEmptyView(View view) {
        return view.findViewById(R.id.guga_empty_view);
    }


    @NonNull
    public RecyclerView.Adapter<VH> createAdapter() {
        return new GugaDelegatedAdapter(this);
    }
}
