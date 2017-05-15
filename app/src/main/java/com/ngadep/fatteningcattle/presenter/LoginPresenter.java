package com.ngadep.fatteningcattle.presenter;

import com.ngadep.fatteningcattle.contracts.LoginContract;

public class LoginPresenter {

    private final LoginContract.View mLogInView;
    private final LoginContract.Repository mLogInRepository;

    public LoginPresenter(LoginContract.View logInView, LoginContract.Repository logInRepository) {
        mLogInView = logInView;
        mLogInRepository = logInRepository;
    }

    public void tryToLogIn(String email, String password) {
        mLogInView.showProgressDialog();
        if (mLogInRepository.tryLogIn(email, password)) {
            mLogInView.hideProgressDialog();
            mLogInView.startMainActivity();
        } else {
            mLogInView.hideProgressDialog();
            mLogInView.showErrorText();
        }
    }

    public void start() {
        mLogInRepository.checkLogin();
        if (mLogInRepository.isLogin()) {
            mLogInView.startMainActivity();
        }
    }
}
