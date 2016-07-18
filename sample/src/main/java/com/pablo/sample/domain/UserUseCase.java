package com.pablo.sample.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * @author Pablo
 * @since 17/07/2016
 */
public class UserUseCase implements SaveUserUseCase, GetUserUseCase {

    private static final Collection<User> COLLECTION;

    @Override
    public Observable<User> get() {
        return Observable.from(COLLECTION);
    }

    @Override
    public Observable<User> get(int page) {
        return Observable.from(COLLECTION).skip(page * 10);
    }

    @Override
    public Observable<User> save(User user) {
        return Observable.just(user)
                .delay(3, TimeUnit.SECONDS)
                .doOnNext(COLLECTION::add);
    }

    static {
        COLLECTION = new ArrayList<>();
        COLLECTION.add(new User("Pablo", "pablo.baldez@kobe.io"));
        COLLECTION.add(new User("Fabio", "fabio.barboza@kobe.io"));
        COLLECTION.add(new User("Thomas", "thomas.delgado@kobe.io"));
        COLLECTION.add(new User("Bruno", "bruno.bulso@kobe.io"));
    }
}
