package com.pablobaldez.guga.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;

import com.jakewharton.rxbinding.view.RxView;
import com.pablobaldez.guga.R;
import com.pablobaldez.guga.utils.ToastMessageManager;
import com.trello.navi.component.NaviFragment;
import com.trello.navi.internal.NaviEmitter;

import java.util.LinkedList;

/**
 * Created by pablobaldez on 5/12/16.
 *
 * Base MvpFragment using GugaMvpView and NaviFragment to handle the lifecycle and event methods
 */
public abstract class GugaFragment extends NaviFragment implements GugaMvpView {

    private QuickMessageManager quickMessageManager;
    private boolean loadingState;

    private ProgressBar progressBar;

    private LinkedList<View> toChangeEnabledWhenLoad;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        quickMessageManager = new ToastMessageManager(getActivity());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = findProgressBar(view);
    }

    /**
     * Set the default quick message manager to show the
     * @param quickMessageManager New manager
     */
    public void setQuickMessageManager(QuickMessageManager quickMessageManager) {
        this.quickMessageManager = quickMessageManager;
    }

    @Override
    public void setLoadingState(boolean loadingState) {
        this.loadingState = loadingState;
        if (progressBar != null) {
            progressBar.setVisibility(loadingState ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void showGenericErrorMessage() {
        quickMessageManager.showMessage("Default error");
    }

    /**
     * @return true if the current state of view is loading of false if not
     */
    public boolean isLoadingState() {
        return loadingState;
    }

    /**
     * Get the progress bar to indicate the loading state. T
     * @param view View passed in {@link #onViewCreated(View, Bundle)}
     * @return ProgressBar or null
     */
    @Nullable
    public ProgressBar findProgressBar(View view){
        return (ProgressBar) view.findViewById(R.id.guga_progress_bar);
    }


}
