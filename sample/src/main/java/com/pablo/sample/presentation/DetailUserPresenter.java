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

    public DetailUserPresenter(DetailUserView view, SaveUserUseCase useCase, NavigationResultFinisher<User> finisher) {
        this.view = view;
        this.useCase = useCase;
        this.finisher = finisher;
    }

    public void save(User user) {
        RxUtils.saveMainThreadIntoLifecycle(useCase.save(user), view)
                .subscribe(new SubscriberToFinish(view, user));
    }

    private final class SubscriberToFinish extends GugaViewSubscriber<User> {
        final User user;
        public SubscriberToFinish(GugaMvpView view, User user) {
            super(view);
            this.user = user;
        }

        @Override
        public void onCompleted() {
            super.onCompleted();
            finisher.finish(user);
        }
    }
}
