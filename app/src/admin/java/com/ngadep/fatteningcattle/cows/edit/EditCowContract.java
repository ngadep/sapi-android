package com.ngadep.fatteningcattle.cows.edit;

import com.ngadep.fatteningcattle.BasePresenter;
import com.ngadep.fatteningcattle.BaseView;

public interface EditCowContract {
    interface Presenter extends BasePresenter {

        void saveCow(String earTag, String sex, int weight, long date);
    }

    interface View extends BaseView<Presenter> {

    }
}
