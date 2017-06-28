package com.ngadep.fatteningcattle.users;

import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.BasePresenter;
import com.ngadep.fatteningcattle.BaseView;

interface UserContract {
    interface Presenter extends BasePresenter {

        void showPackageActivity(String userKey);
    }

    interface View extends BaseView<Presenter> {
        void showAllUser(Query users);

        void startPackageActivity(String userId);

    }
}
