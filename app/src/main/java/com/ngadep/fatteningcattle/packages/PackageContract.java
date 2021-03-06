package com.ngadep.fatteningcattle.packages;

import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.BasePresenter;
import com.ngadep.fatteningcattle.BaseView;
import com.ngadep.fatteningcattle.models.User;

interface PackageContract {
    interface View extends BaseView<Presenter> {

        void getPackages(Query packages);

        void startCowActivity(String packageKey);

        void startEditPackageActivity(String userId);

        void notifyUserChange(User model);
    }

    interface Presenter extends BasePresenter {

        void getCurrentUserPackages();

        void showCowActivity(String packageKey) ;

        void showEditPackageActivity();

        void cleanup();
    }
}
