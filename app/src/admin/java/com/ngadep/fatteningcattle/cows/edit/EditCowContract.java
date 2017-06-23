package com.ngadep.fatteningcattle.cows.edit;

import com.ngadep.fatteningcattle.BasePresenter;
import com.ngadep.fatteningcattle.BaseView;

public interface EditCowContract {
    interface Presenter extends BasePresenter {

        void saveCow(String earTag, String sex, int weight, long date);

        void cleanup();
    }

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void setEarTag(String earTag);

        void setSex(String sex);

        void setWeight(int weight);

        void setDate(Long date);

        void showCowList();

        void hideWeightAndDate();
    }
}
