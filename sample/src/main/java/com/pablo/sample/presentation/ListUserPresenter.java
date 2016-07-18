package com.pablo.sample.presentation;

import android.util.Log;

import com.pablo.sample.domain.GetUserUseCase;
import com.pablo.sample.domain.User;
import com.pablobaldez.guga.presenter.GugaPagedListPresenter;
import com.pablobaldez.guga.presenter.NavigationForResult;
import com.pablobaldez.guga.view.GugaListMvpView;

import rx.Observable;

/**
 * @author Pablo
 * @since 17/07/2016
 */
public class ListUserPresenter extends GugaPagedListPresenter<User> {

    private final GetUserUseCase useCase;
    private final NavigationForResult navigation;

    public ListUserPresenter(GugaListMvpView view,
                             GetUserUseCase useCase,
                             NavigationForResult navigation) {
        super(view);
        this.useCase = useCase;
        this.navigation = navigation;
    }

    public void init(){
        navigation.extract().subscribe(user -> {
            Log.d("pablo", "on activity result");
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

    public void onClickItem(int position) {
        navigation.startForResult();
    }
}
