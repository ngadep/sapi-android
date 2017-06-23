package com.ngadep.fatteningcattle.progresses;

import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.BasePresenter;
import com.ngadep.fatteningcattle.BaseView;
import com.ngadep.fatteningcattle.models.Cow;

public interface ProgressContract {
    interface Presenter extends BasePresenter {

        Long getPricePerKg();

        void startProgressDetailActivity(String progressId);

        void cleanup();

        void startAddProgressUi();
    }

    interface View extends BaseView<Presenter> {

        void getAllCowProgress(Query query);

        void notifyPriceChange();

        void notifyCowChange(Cow model);

        void showEditProgressActivity(String cowId, String progressId);
    }
}
