package com.pablobaldez.guga.navigation;

import android.app.Activity;

/**
 * @author pablobaldez
 * @since 7/18/16
 */
public class GugaNavigatinFinisher implements NavigationFinisher {

    private final Activity activity;

    public GugaNavigatinFinisher(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void finish() {
        activity.setResult(Activity.RESULT_OK);
        activity.finish();
    }
}
