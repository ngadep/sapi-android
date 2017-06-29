package com.ngadep.fatteningcattle.cows;

import com.google.firebase.database.Query;
import com.google.firebase.storage.StorageReference;
import com.ngadep.fatteningcattle.BasePresenter;
import com.ngadep.fatteningcattle.BaseView;
import com.ngadep.fatteningcattle.models.Package;

public interface CowContract{

    interface Presenter extends BasePresenter {

        void showProgressActivity(String cowId);

        void showEditCowActivity();

        void showEditPackageActivity();

        Long getPricePerKg();

        StorageReference getCowImage(String cowId);

        void cleanup();
    }

    interface View extends BaseView<Presenter>{

        void startEditCowActivity(String packageId);

        void startEditPackageActivity(String packageId, String userId);

        void startProgressActivity(String cowId);

        void getAllPackageCow(Query cows);

        void notifyPriceChange();

        void notifyPackageChange(Package aPackage);
    }

}
