package com.pablobaldez.guga.view.qmm;

import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * @author pablobaldez
 * @since 7/13/16
 */
public class SnackbarMessageManager implements QuickMessageManager {

    private final View view;

    public SnackbarMessageManager(View view) {
        this.view = view;
    }

    @Override
    public void showMessage(@StringRes int messageResId) {
        Snackbar.make(view, messageResId, Snackbar.LENGTH_SHORT);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
    }
}
