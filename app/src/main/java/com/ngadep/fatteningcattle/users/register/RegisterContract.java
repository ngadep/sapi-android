package com.ngadep.fatteningcattle.users.register;


import com.ngadep.fatteningcattle.BasePresenter;
import com.ngadep.fatteningcattle.BaseView;
import com.ngadep.fatteningcattle.models.User;

interface RegisterContract {
    interface Presenter extends BasePresenter{

        void saveUser(User user, String password);
    }

    interface View extends BaseView<Presenter> {

        void loginSuccess();

        void showLoginFailed();
    }
}
