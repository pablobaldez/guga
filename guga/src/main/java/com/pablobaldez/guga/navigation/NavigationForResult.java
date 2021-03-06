package com.pablobaldez.guga.navigation;


import rx.Observable;

/**
 * @author Pablo
 * @since 26/05/2016
 */
public interface NavigationForResult<T> {
    void startForResult();
    Observable<T> extract();
}
