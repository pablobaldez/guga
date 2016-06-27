package com.pablobaldez.guga.view;

import com.trello.navi.NaviComponent;

/**
 * Basic view. Could be a fragment, activity or an android view
 * @author pablobaldez
 * @since 5/10/16
 */
public interface GugaMvpView extends NaviComponent{

    /**
     * Set view loading state, to show/hide dialogs, enable/disable views, etc..
     * @param loadingState the new state of view
     */
    void setLoadingState(boolean loadingState);

    boolean isLoadingState();

    /**
     * Show some generic message for uninteresting errors/exceptions
     */
    void showGenericErrorMessage();

}
