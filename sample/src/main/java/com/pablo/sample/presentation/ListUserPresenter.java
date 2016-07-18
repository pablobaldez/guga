package com.pablo.sample.presentation;

import com.pablo.sample.domain.GetUserUseCase;
import com.pablo.sample.domain.User;
import com.pablobaldez.guga.navigation.NavigationForResult;
import com.pablobaldez.guga.presenter.GugaPagedListPresenter;
import com.pablobaldez.guga.subscribers.GugaSubscribers;
import com.pablobaldez.guga.view.GugaListMvpView;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * @author Pablo
 * @since 17/07/2016
 */
public class ListUserPresenter extends GugaPagedListPresenter<User> {

    private final GetUserUseCase useCase;
    private final NavigationForResult<User> navigation;

    public ListUserPresenter(GugaListMvpView view,
                             GetUserUseCase useCase,
                             NavigationForResult navigation) {
        super(view);
        this.useCase = useCase;
        this.navigation = navigation;
    }

    public void init(){
        navigation.extract().subscribe(user -> {
            list.add(user);
        }, throwable -> {

        }, new Action0() {
            @Override
            public void call() {
                getView().notifyDataInserted(1);
            }
        });
    }

    @Override
    public Observable<User> loadData() {
        if (getPage() == 0) {
            return useCase.get();
        }
        else {
            return useCase.get(getPage());
        }
    }

    public void onClickItem() {
        navigation.startForResult();
    }
}
