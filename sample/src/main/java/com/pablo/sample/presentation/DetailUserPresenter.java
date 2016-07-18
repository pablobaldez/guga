package com.pablo.sample.presentation;

import com.pablo.sample.domain.SaveUserUseCase;
import com.pablo.sample.domain.User;
import com.pablobaldez.guga.navigation.NavigationResultFinisher;
import com.pablobaldez.guga.subscribers.GugaViewSubscriber;
import com.pablobaldez.guga.utils.RxUtils;
import com.pablobaldez.guga.view.GugaMvpView;

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

    public void save(User user) {
        SubscriberToFinish subscriberToFinish = new SubscriberToFinish(view);

        RxUtils.saveMainThreadIntoLifecycle(useCase.save(user), view)
                .subscribe(subscriberToFinish);
    }

    private final class SubscriberToFinish extends GugaViewSubscriber<User> {

        public SubscriberToFinish(GugaMvpView view) {
            super(view);
        }

        @Override
        public void onCompleted() {
            super.onCompleted();
            finisher.finish(user);
        }
    }
}
