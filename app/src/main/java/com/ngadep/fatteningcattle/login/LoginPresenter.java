package com.ngadep.fatteningcattle.login;

import com.ngadep.fatteningcattle.data.datasources.AuthDataSource;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mLogInView;
    private final AuthDataSource mLogInRepository;

    public LoginPresenter(LoginContract.View logInView, AuthDataSource logInRepository) {
        mLogInView = logInView;
        mLogInRepository = logInRepository;
        logInView.setPresenter(this);
    }

    @Override
    public void tryToLogIn(String email, String password) {
        mLogInView.showProgressDialog();
        mLogInRepository.tryLogIn(email, password, new AuthDataSource.LogInListener() {
            @Override
            public void onLogIn(boolean success) {
                if (success) {
                    mLogInView.hideProgressDialog();
                    mLogInView.startMainActivity();
                } else {
                    mLogInView.hideProgressDialog();
                    mLogInView.showErrorText();
                }
            }
        });
    }

    @Override
    public void start() {
        if (mLogInRepository.isLogin()) {
            mLogInView.startMainActivity();
        }
    }
}
