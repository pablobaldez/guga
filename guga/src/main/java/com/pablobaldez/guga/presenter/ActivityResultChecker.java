package com.pablobaldez.guga.presenter;

import android.app.Activity;
import android.content.Intent;

import com.trello.navi.model.ActivityResult;

/**
 * @author pablobaldez
 * @since 7/18/16
 */
public class ActivityResultChecker {

    private final int resultCode;
    private final int requestCode;
    private final Intent data;

    public ActivityResultChecker(ActivityResult activityResult) {
        this.resultCode = activityResult.resultCode();
        this.requestCode = activityResult.requestCode();
        this.data = activityResult.data();
    }

    public ActivityResultChecker(int resultCode, int requestCode, Intent data) {
        this.resultCode = resultCode;
        this.requestCode = requestCode;
        this.data = data;
    }

    public boolean activityResponseOk(int expectedRequestCode){
        boolean resultOk = resultCode == Activity.RESULT_OK;
        boolean requestOk = requestCode == expectedRequestCode;
        boolean dataNotNull = data != null;

        return resultOk && requestOk && dataNotNull;
    }

    public boolean activityResponseOk(int expectedRequestCode, String... expectedKeys){
        boolean resultOK = activityResponseOk(expectedRequestCode);
        boolean containKeys = containKeys(data, expectedKeys);
        return resultOK && containKeys;
    }

    public boolean containKeys(Intent data, String... expectedKeys) {
        for (String key: expectedKeys) {
            if (data.hasExtra(key)) {
                return false;
            }
        }
        return true;
    }

}
