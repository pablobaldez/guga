package com.pablo.sample.presentation;

import com.pablo.sample.domain.SaveUserUseCase;
import com.pablo.sample.domain.User;
import com.pablobaldez.guga.subscribers.GugaViewSubscriber;
import com.pablobaldez.guga.utils.RxUtils;

import rx.Subscriber;

/**
 * @author Pablo
 * @since 17/07/2016
 */
public class DetailUserPresenter {

    private final DetailUserView view;
    private final SaveUserUseCase useCase;

    private User user;

    public DetailUserPresenter(DetailUserView view, SaveUserUseCase useCase) {
        this.view = view;
        this.useCase = useCase;
        user = new User();
    }

    public void init() {
        view.bind(user);
    }

    public void save() {
        Subscriber<User> subscriber = new GugaViewSubscriber<>(view);
        RxUtils.saveMainThreadIntoLifecycle(useCase.save(user), view)
                .subscribe(subscriber);
    }
}
