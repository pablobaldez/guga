package com.pablobaldez.guga.navigation;

import android.app.Activity;
import android.content.Intent;

/**
 * @author pablobaldez
 * @since 7/18/16
 */
public abstract class GugaNavigationResultFinisher<T> implements NavigationResultFinisher<T> {

    private final Activity activity;

    public GugaNavigationResultFinisher(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void finish(T data) {
        Intent intent = mapToIntent(data);
        activity.setResult(Activity.RESULT_OK, intent);
        activity.finish();
    }

    public abstract Intent mapToIntent(T data);
}
