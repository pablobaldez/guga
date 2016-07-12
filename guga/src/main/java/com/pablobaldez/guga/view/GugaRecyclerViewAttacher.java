package com.pablobaldez.guga.view;

import android.support.v7.widget.RecyclerView;

import com.mugen.MugenCallbacks;
import com.mugen.attachers.RecyclerViewAttacher;

/**
 * @author pablobaldez
 * @since 7/12/16
 */
public class GugaRecyclerViewAttacher extends RecyclerViewAttacher{

    private RecyclerView recyclerView;

    public GugaRecyclerViewAttacher(RecyclerView recyclerView, MugenCallbacks callbacks) {
        super(recyclerView, callbacks);
        this.recyclerView = recyclerView;
    }

    public void stop() {
        setLoadMoreEnabled(false);
        recyclerView.removeOnScrollListener(mOnScrollListener);
    }
}
