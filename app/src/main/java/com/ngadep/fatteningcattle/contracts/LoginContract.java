package com.ngadep.fatteningcattle.contracts;

public class LoginContract {
    public interface View {
        void trySignIn();

        void showLoginFailed(int code);
    }

    public interface Presenter {

        boolean isLogin();

        void onLogInFailed(int code);
    }

    public interface Repository{

        boolean isLogin();
    }
}
