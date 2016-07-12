package com.pablobaldez.guga.view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Delegate all basic methods from adapter to another class. Easy way to use MVP with adapters
 * @author pablobaldez
 * @since 5/18/16
 */
public class GugaDelegatedAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private final Delegate<VH> delegate;

    public GugaDelegatedAdapter(Delegate<VH> delegate) {
        this.delegate = delegate;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegate.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        delegate.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return delegate.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return delegate.getItemViewType(position);
    }

    /**
     * Will receive the basic adapter methods to handle the item views from a MvpView class
     * @param <VH> View holder to handle the list items
     */
    public interface Delegate<VH extends RecyclerView.ViewHolder> {

        /**
         * delegated method
         * @param parent adapter param
         * @param viewType adapter param
         * @return adapter return
         * @see android.support.v7.widget.RecyclerView.Adapter#onCreateViewHolder(ViewGroup, int)
         */
        VH onCreateViewHolder(ViewGroup parent, int viewType);

        /**
         * delegated method
         * @param holder adapter param
         * @param position adapter param
         * @see android.support.v7.widget.RecyclerView.Adapter#onBindViewHolder(RecyclerView.ViewHolder, int)
         */
        void onBindViewHolder(VH holder, int position);

        /**
         * delegated method
         * @return adapter return
         * @see android.support.v7.widget.RecyclerView.Adapter#getItemCount
         */
        int getItemCount();

        /**
         * delegated method
         * @param position adapter param
         * @return adapter return
         * @see android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)
         */
        int getItemViewType(int position);
    }
}
