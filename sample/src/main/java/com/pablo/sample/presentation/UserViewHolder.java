package com.pablo.sample.presentation;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.pablo.sample.R;
import com.pablo.sample.databinding.ItemUserBinding;
import com.pablo.sample.domain.User;

/**
 * @author Pablo
 * @since 17/07/2016
 */
public class UserViewHolder extends RecyclerView.ViewHolder{

    public static UserViewHolder newInstance(LayoutInflater inflater, ViewGroup parent) {
        ItemUserBinding userBinding = DataBindingUtil.inflate(inflater, R.layout.item_user, parent, false);
        return new UserViewHolder(userBinding);
    }

    private final ItemUserBinding userBinding;

    private UserViewHolder(ItemUserBinding userBinding) {
        super(userBinding.getRoot());
        this.userBinding = userBinding;
        this.userBinding.executePendingBindings();
    }

    public void bindView(User user){
        userBinding.setUser(user);

    }

}
