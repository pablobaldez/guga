package com.pablobaldez.guga.view;

import com.trello.navi.NaviComponent;

/**
 * Created by pablobaldez on 5/10/16.
 * Basic view. Could be a fragment, activity or an android view
 */
public interface GugaMvpView extends NaviComponent{

    /**
     * Set view loading state, to show/hide dialogs, enable/disable views, etc..
     * @param loadingState the new state of view
     */
    void setLoadingState(boolean loadingState);

    /**
     * Show some generic message for uninteresting errors/exceptions
     */
    void showGenericErrorMessage();

}
