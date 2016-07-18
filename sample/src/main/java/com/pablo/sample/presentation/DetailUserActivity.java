package com.pablo.sample.presentation;

import android.app.Fragment;

import com.pablobaldez.guga.view.activities.SingleFragmentToolbarActivity;

/**
 * @author Pablo
 * @since 17/07/2016
 */
public class DetailUserActivity extends SingleFragmentToolbarActivity {
    @Override
    protected Fragment createFragment() {
        return new DetailUserFragment();
    }
}
