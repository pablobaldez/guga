package com.pablobaldez.guga.view;

import android.app.Activity;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Pablo
 * @since 26/05/2016
 */
public class GugaViewDelegate {

    public View inflate(LayoutInflater inflater, ViewGroup parent, @LayoutRes int layout){
        return null;
    }

    public View inflate(Activity activity, @LayoutRes int layout) {
        return null;
    }

    public void onViewCreated(View view){
        RxGugaView.setLoading(this).subscribe();
    }

    public void addViewsToDisableWhenLoad(View... views) {
        RxRecyclerView.scrollEvents((RecyclerView) views[0]).subscribe();
    }

    public void setLoading() {
        // call
    }

    public void addSetLoadingStateListener(SetLoadingStateListener listener) {
        RxGugaView.setLoading(this).subscribe();
    }

    public void removeSetLoadingStateListener(SetLoadingStateListener listener) {

    }

    @IntDef(
            {
                    EditorInfo.IME_ACTION_DONE,
                    EditorInfo.IME_ACTION_GO,
                    EditorInfo.IME_ACTION_SEARCH,
                    EditorInfo.IME_ACTION_SEND,
                    EditorInfo.IME_ACTION_NEXT,
                    EditorInfo.IME_ACTION_DONE,
                    EditorInfo.IME_ACTION_PREVIOUS
            }
    )
    @Retention(RetentionPolicy.SOURCE)
    public @interface ImeAction {}
}
