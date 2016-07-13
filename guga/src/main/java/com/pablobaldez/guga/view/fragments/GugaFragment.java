package com.pablobaldez.guga.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;

import com.pablobaldez.guga.R;
import com.pablobaldez.guga.view.GugaMvpView;
import com.pablobaldez.guga.view.qmm.QuickMessageManager;
import com.pablobaldez.guga.view.qmm.SnackbarMessageManager;
import com.pablobaldez.guga.view.qmm.ToastMessageManager;
import com.trello.navi.component.NaviFragment;

/**
 * Base MvpFragment using GugaMvpView and NaviFragment to handle the lifecycle and event methods
 * @author pablobaldez
 * @since 5/12/16
 */
public abstract class GugaFragment extends NaviFragment implements GugaMvpView {

    @Nullable  private QuickMessageManager quickMessageManager;
    private boolean loadingState;

    private ProgressBar progressBar;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        quickMessageManager = new SnackbarMessageManager(view);
        progressBar = findProgressBar(view);
    }

    @Override
    public void setLoadingState(boolean loadingState) {
        this.loadingState = loadingState;
        if(shouldChangeProgressBar()) {
            progressBar.setVisibility(loadingState ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void setErrorState() {
        setLoadingState(false);
        if (quickMessageManager != null) {
            quickMessageManager.showMessage(R.string.guga_error_default_message);
        }
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

    /**
     * @return true if the current state of view is loading of false if not
     */
    public boolean isLoadingState() {
        return loadingState;
    }

    /**
     * Set the default quick message manager to show the
     * @param quickMessageManager New manager
     */
    public void setQuickMessageManager(QuickMessageManager quickMessageManager) {
        this.quickMessageManager = quickMessageManager;
    }

    protected boolean shouldChangeProgressBar() {
        return isAdded() && progressBar != null;
    }

}
