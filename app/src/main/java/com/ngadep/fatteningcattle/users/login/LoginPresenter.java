package com.ngadep.fatteningcattle.users.login;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mLogInView;
    private final LoginRepository mLogInRepository;

    public LoginPresenter(LoginContract.View logInView, LoginRepository logInRepository) {
        mLogInView = logInView;
        mLogInRepository = logInRepository;
        logInView.setPresenter(this);
    }

    @Override
    public void tryToLogIn(String email, String password) {
        mLogInView.showProgressDialog();
        mLogInRepository.tryLogIn(email, password, new LoginRepository.LogInListener() {
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
