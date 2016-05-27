package com.pablobaldez.guga.view;

import android.app.Activity;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewEditorActionEvent;
import com.trello.navi.NaviComponent;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import rx.Subscriber;
import rx.functions.Action0;

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

    }

    public void addViewsToDisableWhenLoad(View... views) {
        RxRecyclerView.scrollEvents((RecyclerView) views[0]).subscribe();
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
