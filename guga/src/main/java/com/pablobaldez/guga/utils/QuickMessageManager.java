package com.pablobaldez.guga.utils;

import android.support.annotation.StringRes;

/**
 * Created by pablobaldez on 5/19/16.
 * Shows quick little messages to user, like toast, Snackbar or dialog
 */
public interface QuickMessageManager {

    /**
     * Show a message usisng a resource
     * @param messageResId resource used to show a message
     */
    void showMessage(@StringRes int messageResId);

    /**
     * Show a message using a text
     * @param message text to show
     */
    void showMessage(String message);
}
