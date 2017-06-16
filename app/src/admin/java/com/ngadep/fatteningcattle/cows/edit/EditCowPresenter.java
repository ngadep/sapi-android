package com.ngadep.fatteningcattle.cows.edit;

import com.ngadep.fatteningcattle.cows.CowRepository;
import com.ngadep.fatteningcattle.models.Cow;

public class EditCowPresenter implements EditCowContract.Presenter {
    private final String mCowId;
    private final Cow mCowModel;
    private final CowRepository mRepository;
    private final EditCowContract.View mView;

    public EditCowPresenter(String cowId, Cow cowModel, CowRepository repository, EditCowContract.View view) {
        mCowId = cowId;
        mCowModel = cowModel;
        mRepository = repository;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
