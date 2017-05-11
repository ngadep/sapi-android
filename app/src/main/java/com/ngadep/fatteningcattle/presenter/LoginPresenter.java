package com.ngadep.fatteningcattle.presenter;

import com.ngadep.fatteningcattle.contracts.LoginContract;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mLogInView;
    private LoginContract.Repository mLogInRepository;

    public LoginPresenter(LoginContract.View logInView, LoginContract.Repository logInRepository) {
        mLogInView = logInView;
        mLogInRepository = logInRepository;
    }

    @Override
    public boolean isLogin() {
        return mLogInRepository.isLogin();
    }

    @Override
    public void onLogInFailed(int code) {
        mLogInView.showLoginFailed(code);
    }
}
