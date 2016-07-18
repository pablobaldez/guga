package com.pablobaldez.guga.presenter;

/**
 * @author pablobaldez
 * @since 7/18/16
 */
public interface NavigationResultFinisher<T> {
    void finish(int resultCode, T t);
}
