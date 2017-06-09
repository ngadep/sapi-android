package com.ngadep.fatteningcattle.users.login;

import com.ngadep.fatteningcattle.users.UserRepository;

class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mLogInView;
    private final UserRepository mLogInRepository;

    LoginPresenter(LoginContract.View logInView, UserRepository logInRepository) {
        mLogInView = logInView;
        mLogInRepository = logInRepository;
        logInView.setPresenter(this);
    }

    @Override
    public void tryToLogIn(String email, String password) {
        mLogInView.showProgressDialog();
        mLogInRepository.tryLogIn(email, password, new UserRepository.LogInListener() {
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
        mLogInRepository.userIsLogin(new UserRepository.LogInListener() {
            @Override
            public void onLogIn(boolean success) {
                if (success) {
                    mLogInView.startMainActivity();
                }
            }
        });
    }
}
