package com.pablo.sample.presentation;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pablo.sample.R;
import com.pablo.sample.SampleApp;
import com.pablo.sample.databinding.FragmentUserDetailBinding;
import com.pablo.sample.domain.User;
import com.pablobaldez.guga.view.fragments.GugaFragment;

/**
 * @author Pablo
 * @since 17/07/2016
 */
public class DetailUserFragment extends GugaFragment implements DetailUserView{

    private DetailUserPresenter presenter;
    private FragmentUserDetailBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SampleApp app = (SampleApp) getActivity().getApplication();
        presenter = new DetailUserPresenter(this,
                app.saveUserUseCase(),
                new ListDetailNavigator.DetailResultResultFinisher(getActivity()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = DataBindingUtil.bind(view);
        presenter.init();
        view.findViewById(R.id.save).setOnClickListener(view1 -> {
            binding.executePendingBindings();
            presenter.save(binding.getUser());
        });
    }

    @Override
    public void bind(User user) {
        binding.setUser(user);
    }
}
