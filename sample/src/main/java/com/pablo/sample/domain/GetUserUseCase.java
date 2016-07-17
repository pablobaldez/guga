package com.pablo.sample.domain;

import rx.Observable;

/**
 * @author Pablo
 * @since 17/07/2016
 */
public interface GetUserUseCase {

    Observable<User> get();

    Observable<User> get(int page);

}
