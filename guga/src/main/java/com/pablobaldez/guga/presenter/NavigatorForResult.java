package com.pablobaldez.guga.presenter;

import android.database.Observable;

/**
 * @author Pablo
 * @since 26/05/2016
 */
public interface NavigatorForResult<T> extends Navigator {

    Observable<T> startForResult();
}
