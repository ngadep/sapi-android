package com.ngadep.fatteningcattle.users;


import com.ngadep.fatteningcattle.BasePresenter;
import com.ngadep.fatteningcattle.BaseView;
import com.ngadep.fatteningcattle.data.models.User;

public interface RegisterContract {
    interface Presenter extends BasePresenter{

        void saveUser(User user, String password);
    }

    interface View extends BaseView<Presenter> {

        void showUser(User user);
    }
}
