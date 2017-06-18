package com.ngadep.fatteningcattle.cows;

import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.BasePresenter;
import com.ngadep.fatteningcattle.BaseView;
public interface CowContract{

    interface Presenter extends BasePresenter {

        void startCowDetailActivity(String cowId);

        void startAddCowUi();

        Long getPricePerKg();

        void cleanup();
    }

    interface View extends BaseView<Presenter>{

        void getAllPackageCow(Query cows);

        void notifyPriceChange();

        void startEditCow();
    }

}
