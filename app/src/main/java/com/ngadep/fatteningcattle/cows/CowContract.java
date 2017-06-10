package com.ngadep.fatteningcattle.cows;

import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.BasePresenter;
import com.ngadep.fatteningcattle.BaseView;
public interface CowContract{

    interface Presenter extends BasePresenter {

        void startCowDetailActivity(String cowId);
    }

    interface View extends BaseView<Presenter>{

        void getAllPackageCow(Query cows);
    }

    interface Repository {

        Query getPackageCowFromId(String packageId);
    }
}
