package com.pablo.sample.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.pablo.sample.SampleApp;
import com.pablo.sample.domain.User;
import com.pablobaldez.guga.view.fragments.GugaSwipeFragment;

/**
 * @author Pablo
 * @since 17/07/2016
 */
public class ListUserFragment extends GugaSwipeFragment<UserViewHolder>{

    private ListUserPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // use dependency injection here
        SampleApp app = (SampleApp) getActivity().getApplication();
        presenter = new ListUserPresenter(this, app.getUserUseCase());
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.refreshView();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return UserViewHolder.newInstance(LayoutInflater.from(getActivity()), parent);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = presenter.get(position);
        holder.userBinding.setUser(user);
    }

    @Override
    public void onRefresh() {
        presenter.refreshView();
    }
}
