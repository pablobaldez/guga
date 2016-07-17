package com.pablo.sample.presentation;

import com.pablo.sample.domain.GetUserUseCase;
import com.pablo.sample.domain.User;
import com.pablobaldez.guga.presenter.GugaPagedListPresenter;
import com.pablobaldez.guga.view.GugaListMvpView;

import rx.Observable;

/**
 * @author Pablo
 * @since 17/07/2016
 */
public class ListUserPresenter extends GugaPagedListPresenter<User> {

    private final GetUserUseCase useCase;

    public ListUserPresenter(GugaListMvpView view, GetUserUseCase useCase) {
        super(view);
        this.useCase = useCase;
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
}
