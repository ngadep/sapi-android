package com.ngadep.fatteningcattle.presenter;

import com.ngadep.fatteningcattle.R;
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
        if (resultCode == LogInStatus.SUCCESS) {
            mLogInView.showTextAndButton(false);
            mLogInView.startMainActivity();
        } else {
            mLogInView.showTextAndButton(true);
            changeErrorText(resultCode);
        }
    }

    private void changeErrorText(int code) {
        if (code == LogInStatus.CANCELLED) {
            mLogInView.showErrorText(R.string.sign_in_cancelled);
            return;
        }

        if (code == LogInStatus.NO_NETWORK) {
            mLogInView.showErrorText(R.string.sign_in_no_network);
            return;
        }

        if (code == LogInStatus.UNKNOWN_ERROR) {
            mLogInView.showErrorText(R.string.sign_in_unknown_error);
        }
    }

    public final class LogInStatus {

        /**
         * Sign in failed, user cancelled
         **/
        public static final int SUCCESS = -1;
        /**
         * Sign in failed, user cancelled
         **/
        public static final int CANCELLED = 0;
        /**
         * Sign in failed due to lack of network connection
         **/
        public static final int NO_NETWORK = 10;

        /**
         * An unknown error has occurred
         **/
        public static final int UNKNOWN_ERROR = 20;

        private LogInStatus() {
            // no instance
        }
    }

}
