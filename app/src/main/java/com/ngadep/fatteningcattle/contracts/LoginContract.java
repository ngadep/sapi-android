package com.ngadep.fatteningcattle.contracts;

public interface LoginContract {
    interface View {

        void showTextAndButton(Boolean visible);

        void tryLogIn();

        void startMainActivity();

        void showErrorText(int resId);
    }

    interface Repository{

        boolean isLogin();
    }
}
