package com.pablobaldez.guga.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerViewAdapter;
import com.pablobaldez.guga.R;
import com.trello.navi.Event;
import com.trello.navi.rx.RxNavi;

/**
 * Created by pablobaldez on 5/12/16.
 * Fragment that shows a list to user using recyclerview 
 */
public abstract class GugaRecyclerFragment<VH extends RecyclerView.ViewHolder>
        extends GugaFragment
        implements GugaListMvpView, GugaDelegatedAdapter.Delegate<VH> {

    private int itemCount = 0;
    private RecyclerView.Adapter<VH> adapter;

    public RecyclerView recyclerView;
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
        findViews(view);
    }

    public void findViews(View view) {
        recyclerView = findRecyclerView(view);
        emptyView = findRecyclerView(view);
    }

    public void setUpRecyclerView() {
        recyclerView.setLayoutManager(createLayoutManager());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(false);
        RxRecyclerViewAdapter
                .dataChanges(adapter)
                .takeUntil(RxNavi.observe(this, Event.STOP))
                .subscribe(
                        vh -> {
                            setEmptyViewVisible(getItemCount() == 0);
                        }
                );
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

    @Override
    public int getItemCount() {
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

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

    /**
     * @return layoutmanager used in this fragment
     */
    @NonNull
    public RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    protected void setEmptyViewVisible(boolean toVisible){
        if (toVisible) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }
}
