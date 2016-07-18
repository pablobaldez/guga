package com.pablo.sample.presentation;

import com.pablo.sample.domain.User;
import com.pablobaldez.guga.view.GugaMvpView;

/**
 * @author Pablo
 * @since 17/07/2016
 */
public interface DetailUserView extends GugaMvpView {

    void bind(User user);

}
