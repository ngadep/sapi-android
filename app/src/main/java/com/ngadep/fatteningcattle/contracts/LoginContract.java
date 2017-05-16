package com.ngadep.fatteningcattle.contracts;

public interface LoginContract {

    interface View extends BaseView<Presenter> {

        void startMainActivity();

        void showErrorText();

        void showProgressDialog();

        void hideProgressDialog();
    }

    interface Presenter extends BasePresenter {

        void tryToLogIn(String email, String password);

    }
}
