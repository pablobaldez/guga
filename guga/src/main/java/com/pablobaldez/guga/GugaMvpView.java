package com.pablobaldez.guga;

import rx.Observable;

/**
 * Created by pablobaldez on 5/10/16.
 * Basic view. Could be a fragment, activity or an android view
 */
public interface GugaMvpView {

    /**
     * Set view loading state, to show/hide dialogs, enable/disable views, etc..
     * @param loadingState the new state of view
     */
    void setLoadingState(boolean loadingState);

    /**
     * Show some generic message for uninteresting errors/exceptions
     */
    void showGenericErrorMessage();

    /**
     * Binds an observable into this view lifecycle
     * @param observable Observable to be bound
     * @param <T> Type of emitted items
     * @return observable bound
     */
    <T> Observable<T> bindIntoLifecycle(Observable<T> observable);

}
