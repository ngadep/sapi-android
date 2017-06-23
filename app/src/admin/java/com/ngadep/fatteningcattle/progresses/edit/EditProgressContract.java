package com.ngadep.fatteningcattle.progresses.edit;


import com.ngadep.fatteningcattle.BasePresenter;
import com.ngadep.fatteningcattle.BaseView;

public interface EditProgressContract {
    interface Presenter extends BasePresenter {

        void saveProgress(int weight, long date);

        void cleanup();
    }

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void setWeight(int weight);

        void setDate(Long date);

        void showProgressList();
    }
}
