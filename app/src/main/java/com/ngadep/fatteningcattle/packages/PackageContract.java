package com.ngadep.fatteningcattle.packages;

import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.BasePresenter;
import com.ngadep.fatteningcattle.BaseView;
import com.ngadep.fatteningcattle.models.Package;

interface PackageContract {
    interface View extends BaseView<Presenter> {

        void getPackages(Query packages);

        void startCowActivity(String packageKey, Package pkg);

        void showAddPackageUi(String userId);
    }

    interface Presenter extends BasePresenter {

        void getCurrentUserPackages();

        void startCowActivity(String packageKey, Package pkg) ;

        void showAddPackage();
    }
}
