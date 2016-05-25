package com.pablobaldez.guga.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by pablobaldez on 5/19/16.
 * default implementation of QuickMessageManager
 */
public class ToastMessageManager implements QuickMessageManager {

    private final Context context;

    public ToastMessageManager(Context context) {
        this.context = context;
    }

    @Override
    public void showMessage(@StringRes int messageResId) {
        Toast.makeText(context, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
