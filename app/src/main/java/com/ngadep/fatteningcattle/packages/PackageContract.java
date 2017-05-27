package com.ngadep.fatteningcattle.packages;

import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.BasePresenter;
import com.ngadep.fatteningcattle.BaseView;

interface PackageContract {
    interface View extends BaseView<Presenter> {

        void getPackages(Query packages);

        void startCowActivity(String packageKey, String packageName);
    }

    interface Presenter extends BasePresenter {

        void getCurrentUserPackages();

        void startCowActivity(String packageKey, String packageName) ;
    }
}
