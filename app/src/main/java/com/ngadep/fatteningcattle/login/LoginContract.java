package com.ngadep.fatteningcattle.login;

import com.ngadep.fatteningcattle.BasePresenter;
import com.ngadep.fatteningcattle.BaseView;

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
