package com.pablo.sample.presentation;

import com.pablo.sample.domain.SaveUserUseCase;
import com.pablo.sample.domain.User;
import com.pablobaldez.guga.navigation.NavigationResultFinisher;
import com.pablobaldez.guga.subscribers.GugaSubscribers;
import com.pablobaldez.guga.utils.RxUtils;

import rx.Observable;
import rx.Subscriber;

/**
 * @author Pablo
 * @since 17/07/2016
 */
public class DetailUserPresenter {

    private final DetailUserView view;
    private final SaveUserUseCase useCase;
    private final NavigationResultFinisher<User> finisher;

    private User currentUser;

    public DetailUserPresenter(DetailUserView view, SaveUserUseCase useCase, NavigationResultFinisher<User> finisher) {
        this.view = view;
        this.useCase = useCase;
        this.finisher = finisher;
    }

    public void init(){
        currentUser = new User();
        view.bind(currentUser);
    }

    public void save() {
        Observable<User> saveObservable = useCase.save(currentUser);
        Subscriber<User> subscriber = GugaSubscribers.onComplete(() -> finisher.finish(currentUser));
        RxUtils.saveMainThreadIntoLifecycle(saveObservable, view)
                .subscribe(subscriber);
    }
}
