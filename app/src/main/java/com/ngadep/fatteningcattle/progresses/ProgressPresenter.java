package com.ngadep.fatteningcattle.progresses;

import com.ngadep.fatteningcattle.BasePresenter;

public class ProgressPresenter implements BasePresenter {

    private ProgressContract.View mView;
    private ProgressRepository mRepository;
    private String mProgressId;

    public ProgressPresenter(ProgressContract.View view, ProgressRepository repository, String progressId) {
        this.mView = view;
        this.mRepository = repository;
        this.mProgressId = progressId;
    }

    @Override
    public void start() {

    }
}
