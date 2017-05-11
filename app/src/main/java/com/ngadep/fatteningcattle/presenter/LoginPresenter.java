package com.ngadep.fatteningcattle.presenter;

import com.ngadep.fatteningcattle.contracts.LoginContract;

public class LoginPresenter {

    private LoginContract.View mLogInView;
    private LoginContract.Repository mLogInRepository;

    public LoginPresenter(LoginContract.View logInView, LoginContract.Repository logInRepository) {
        mLogInView = logInView;
        mLogInRepository = logInRepository;
    }

    private boolean isLoggedIn() {
        return mLogInRepository.isLogin();
    }

    public void tryToLogIn() {
        mLogInView.tryLogIn();
    }

    public void start() {
        if (this.isLoggedIn()) {
            mLogInView.startMainActivity();
        } else {
            mLogInView.tryLogIn();
        }
    }

    public void onLoginResult(int resultCode) {
        int LOGIN_OK = -1;
        if (resultCode == LOGIN_OK) {
            mLogInView.showTextAndButton(false);
            mLogInView.startMainActivity();
        } else {
            mLogInView.showTextAndButton(true);
            mLogInView.showLoginFailed(resultCode);
        }
    }

}
