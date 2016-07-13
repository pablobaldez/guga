package com.pablobaldez.guga.view.activities;

import android.app.Fragment;
import android.os.Bundle;

import com.pablobaldez.guga.R;

/**
 * @author pablobaldez
 * @since 7/13/16
 */
public abstract class SingleFragmentToolbarActivity extends GugaToolbarActivity{

    public static final String FRAGMENT_TAG = "GUGA_FRAGMENT_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guga_toolbar);
        if(savedInstanceState == null){
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.guga_container, createFragment(), FRAGMENT_TAG)
                    .commit();
        }
    }

    /**
     * Creates the fragment to be hosted
     * @return Main fragment of screen
     */
    protected abstract Fragment createFragment();

}
