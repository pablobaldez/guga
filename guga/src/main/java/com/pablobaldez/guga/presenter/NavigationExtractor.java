package com.pablobaldez.guga.presenter;

import android.database.Observable;

/**
 * Extract params from the previous screen and emit it using RxJava
 *
 * @author Pablo
 * @since 26/05/2016
 * @param <T> the type of parameter
 */
public interface NavigationExtractor<T> {

    /**
     * Extract the param
     *
     * <dl>
     *  <dt><b>Scheduler:</b></dt>
     *  <dd>{@code create} does not operate by default on a particular Scheduler.</dd>
     * </dl>
     *
     * @return  an Observable that, when a Subscriber subscribes to it, will execute the
     * specified function
     */
    Observable<T> extract();

}
