package com.pablo.sample.domain;

import java.util.ArrayList;
import java.util.Collection;

import rx.Observable;
import rx.functions.Action1;

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
        return Observable.just(user).doOnNext(new Action1<User>() {
            @Override
            public void call(User user) {
                COLLECTION.add(user);
            }
        });
    }

    static {
        COLLECTION = new ArrayList<>();
        COLLECTION.add(new User("user1@kobe.io", "abc1"));
        COLLECTION.add(new User("user2@kobe.io", "abc2"));
        COLLECTION.add(new User("user3@kobe.io", "abc3"));
    }
}
