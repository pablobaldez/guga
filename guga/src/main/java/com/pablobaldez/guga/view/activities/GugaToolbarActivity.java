package com.pablobaldez.guga.view.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.pablobaldez.guga.R;

/**
 * @author pablobaldez
 * @since 7/13/16
 */
public class GugaToolbarActivity extends GugaAppCompatActivity {

    private Toolbar toolbar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        toolbar = findToolbar();
        setUpToolbar(toolbar);
    }

    @NonNull
    protected Toolbar findToolbar(){
        return (Toolbar) findViewById(R.id.guga_toolbar);
    }

    protected void setUpToolbar(Toolbar toolbar){
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_HOME_AS_UP);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
