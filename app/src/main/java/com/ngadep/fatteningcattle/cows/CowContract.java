package com.ngadep.fatteningcattle.cows;

import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.BasePresenter;
import com.ngadep.fatteningcattle.BaseView;
import com.ngadep.fatteningcattle.models.Package;

public interface CowContract{

    interface Presenter extends BasePresenter {

        void startCowProgress(String cowId);

        void startAddCowUi();

        Long getPricePerKg();

        void cleanup();

        void showEditPackageUi();
    }

    interface View extends BaseView<Presenter>{

        void getAllPackageCow(Query cows);

        void notifyPriceChange();

        void startEditCow(String packageId);

        void showEditPackage(String packageId, Package packageModel);

        void startCowProgressActivity(String cowId);
    }

}
