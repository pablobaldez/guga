package com.pablo.sample.domain;

import rx.Observable;

/**
 * @author Pablo
 * @since 17/07/2016
 */
public interface SaveUserUseCase {

    Observable<User> save(User user);

}
