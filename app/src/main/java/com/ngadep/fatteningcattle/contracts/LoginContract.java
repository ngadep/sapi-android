package com.ngadep.fatteningcattle.contracts;

public interface LoginContract {
    interface View {

        void startMainActivity();

        void showErrorText();

        void showProgressDialog();

        void hideProgressDialog();
    }

    interface Repository {

        boolean isLogin();

        boolean tryLogIn(String email, String password);

        void checkLogin();
    }
}
