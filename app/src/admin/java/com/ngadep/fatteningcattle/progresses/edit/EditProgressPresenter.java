package com.ngadep.fatteningcattle.progresses.edit;

import com.ngadep.fatteningcattle.progresses.ProgressRepository;

public class EditProgressPresenter implements EditProgressContract.Presenter {

    private final String mCowId;
    private final String mProgressId;
    private final ProgressRepository mRepository;
    private final EditProgressContract.View mView;

    public EditProgressPresenter(String cowId, String progressId,
                                 ProgressRepository repository, EditProgressContract.View view) {
        mCowId = cowId;
        mProgressId = progressId;
        mRepository = repository;
        mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
