package com.ngadep.fatteningcattle.progresses;

import com.ngadep.fatteningcattle.BasePresenter;
import com.ngadep.fatteningcattle.cows.CowRepository;
import com.ngadep.fatteningcattle.models.Progress;

public class ProgressPresenter implements BasePresenter {

    private ProgressContract.View mView;
    private CowRepository mRepository;
    private String mProgressId;
    private Progress mProgress;

    public ProgressPresenter(ProgressContract.View view, CowRepository repository, String progressId, Progress progress) {
        this.mView = view;
        this.mRepository = repository;
        this.mProgressId = progressId;
        this.mProgress = progress;
    }

    @Override
    public void start() {

    }
}
