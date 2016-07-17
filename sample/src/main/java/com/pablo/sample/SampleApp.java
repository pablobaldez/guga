package com.pablo.sample;

import android.app.Application;

import com.pablo.sample.domain.GetUserUseCase;
import com.pablo.sample.domain.SaveUserUseCase;
import com.pablo.sample.domain.UserUseCase;

/**
 * @author Pablo
 * @since 17/07/2016
 */
public class SampleApp extends Application {

    private UserUseCase userUseCase;

    @Override
    public void onCreate() {
        super.onCreate();
        userUseCase = new UserUseCase();
    }

    public GetUserUseCase getUserUseCase(){
        return userUseCase;
    }

    public SaveUserUseCase saveUserUseCase(){
        return userUseCase;
    }
}
