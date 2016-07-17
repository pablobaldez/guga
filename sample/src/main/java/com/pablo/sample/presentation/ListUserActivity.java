package com.pablo.sample.presentation;

import android.app.Fragment;

import com.pablobaldez.guga.view.activities.SingleFragmentToolbarActivity;

public class ListUserActivity extends SingleFragmentToolbarActivity {

    @Override
    protected Fragment createFragment() {
        return new ListUserFragment();
    }
}
