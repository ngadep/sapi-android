package com.ngadep.fatteningcattle.contracts;

public class LoginContract {
    public interface View {

        void showTextAndButton(Boolean visible);

        void tryLogIn();

        void startMainActivity();

        void showErrorText(int resId);
    }

    public interface Repository{

        boolean isLogin();
    }
}
