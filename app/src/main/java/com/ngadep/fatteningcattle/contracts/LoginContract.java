package com.ngadep.fatteningcattle.contracts;

public class LoginContract {
    public interface View {

        void showTextAndButton(Boolean visible);

        void tryLogIn();

        void showLoginFailed(int resultCode);

        void startMainActivity();
    }

    public interface Repository{

        boolean isLogin();
    }
}
