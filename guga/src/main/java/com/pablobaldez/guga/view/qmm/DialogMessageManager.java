package com.pablobaldez.guga.view.qmm;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.pablobaldez.guga.R;

/**
 * @author pablobaldez
 * @since 7/13/16
 */
public class DialogMessageManager extends DialogFragment implements QuickMessageManager {

    public static final String FRAGMENT_TAG = "GUGA_DIALOG_TAG";

    @StringRes
    private int messageResId = R.string.guga_error_default_message;
    private String message;

    private TextView textView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Activity activity = getActivity();
        View v = createView(LayoutInflater.from(activity));
        textView = findTextView(v);
        setUpTextView();
        return createBuilder(v).create();
    }

    @SuppressLint("InflateParams")
    @NonNull
    protected View createView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.guga_dialog_message, null);
    }

    @NonNull
    protected TextView findTextView(View rootView) {
        return (TextView) rootView.findViewById(R.id.guga_text);
    }

    protected void setUpTextView() {
        if (message == null) {
            textView.setText(messageResId);
        }
        else {
            textView.setText(message);
        }
    }

    @NonNull
    protected AlertDialog.Builder createBuilder(View rootView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), getThemeStyle());
        builder.setView(rootView).setNeutralButton(android.R.string.ok, (dialog, which) -> {
            onClickOk(dialog);
        });
        return builder;
    }

    @Override
    public void showMessage(@StringRes int messageResId) {
        this.messageResId = messageResId;
        show(getFragmentManager(), FRAGMENT_TAG);
    }

    @Override
    public void showMessage(String message) {
        this.message = message;
        show(getFragmentManager(), FRAGMENT_TAG);
    }

    @StyleRes
    public int getThemeStyle() {
        return R.style.Guga_Theme_Dialog;
    }

    public void onClickOk(DialogInterface dialog) {
        dialog.dismiss();
    }
}
