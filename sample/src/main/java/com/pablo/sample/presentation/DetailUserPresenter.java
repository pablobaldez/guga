package com.pablo.sample.presentation;

import com.pablo.sample.domain.SaveUserUseCase;
import com.pablo.sample.domain.User;
import com.pablobaldez.guga.presenter.NavigationResultFinisher;
import com.pablobaldez.guga.subscribers.GugaViewSubscriber;
import com.pablobaldez.guga.utils.RxUtils;
import com.pablobaldez.guga.view.GugaMvpView;

import rx.Subscriber;

/**
 * @author Pablo
 * @since 17/07/2016
 */
public class DetailUserPresenter {

    private final DetailUserView view;
    private final SaveUserUseCase useCase;
    private final NavigationResultFinisher<User> finisher;

    private User user;

    public DetailUserPresenter(DetailUserView view, SaveUserUseCase useCase, NavigationResultFinisher<User> finisher) {
        this.view = view;
        this.useCase = useCase;
        this.finisher = finisher;
        user = new User();
    }

    public void init() {
        view.bind(user);
    }

    public void save() {
        Finisher finisher = new Finisher(view);
        RxUtils.saveMainThreadIntoLifecycle(useCase.save(user), view)
                .subscribe(finisher);
    }

    private final class Finisher extends GugaViewSubscriber<User> {

        public Finisher(GugaMvpView view) {
            super(view);
        }

        @Override
        public void onCompleted() {
            super.onCompleted();
            finisher.finish(user);
        }
    }
}
