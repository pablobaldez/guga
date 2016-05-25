package com.pablobaldez.guga;

import android.app.Fragment;
import android.os.Bundle;

import com.pablobaldez.guga.utils.QuickMessageManager;
import com.pablobaldez.guga.utils.ToastMessageManager;

import rx.Observable;

/**
 * Created by pablobaldez on 5/12/16.
 */
public abstract class GugaFragment extends Fragment implements GugaMvpView {

    private QuickMessageManager quickMessageManager;
    private boolean loadingState;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        quickMessageManager = new ToastMessageManager(getActivity());
    }

    public void setQuickMessageManager(QuickMessageManager quickMessageManager) {
        this.quickMessageManager = quickMessageManager;
    }

    @Override
    public void setLoadingState(boolean loadingState) {
        loadingState = loadingState;
    }

    public boolean isLoadingState() {
        return loadingState;
    }

    @Override
    public void showGenericErrorMessage() {
        quickMessageManager.showMessage("Default error");
    }

    @Override
    public <T> Observable<T> bindIntoLifecycle(Observable<T> observable) {
        return null;
    }
}
