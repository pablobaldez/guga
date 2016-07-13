package com.pablobaldez.guga.view.activities;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.pablobaldez.guga.R;
import com.pablobaldez.guga.view.GugaMvpView;
import com.pablobaldez.guga.view.qmm.QuickMessageManager;
import com.pablobaldez.guga.view.qmm.SnackbarMessageManager;
import com.trello.navi.component.NaviActivity;

/**
 * @author pablobaldez
 * @since 7/12/16
 */
public class GugaActivity extends NaviActivity implements GugaMvpView{

    private QuickMessageManager quickMessageManager;
    private boolean loadingState;

    private ProgressBar progressBar;

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        quickMessageManager = new SnackbarMessageManager(view);
        progressBar = findProgressBar();
    }

    @Override
    public void setLoadingState(boolean loadingState) {
        this.loadingState = loadingState;
        if (progressBar != null) {
            progressBar.setVisibility(loadingState ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void setErrorState() {
        setLoadingState(false);
        if (quickMessageManager != null) {
            quickMessageManager.showMessage(getString(R.string.guga_error_default_message));
        }
    }

    /**
     * Get the progress bar to indicate the loading state. T
     * @return ProgressBar or null
     */
    @Nullable
    public ProgressBar findProgressBar(){
        return (ProgressBar) findViewById(R.id.guga_progress_bar);
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

}
