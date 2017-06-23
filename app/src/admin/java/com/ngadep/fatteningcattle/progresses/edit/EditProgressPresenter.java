package com.ngadep.fatteningcattle.progresses.edit;

import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.models.Progress;
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
        if (!isNewProgress()) {
            populateProgress();
        }
    }

    private void populateProgress() {
        mRepository.getProgressFromId(mProgressId, new BaseRepository.ModelListener<Progress>() {
            @Override
            public void onModelChange(Progress model) {
                if (mView.isActive()) {
                    mView.setWeight(model.getWeight());
                    mView.setDate(model.getDate());
                }
            }
        });
    }

    @Override
    public void saveProgress(int weight, long date) {

    }

    @Override
    public void cleanup() {
        mRepository.cleanup();
    }

    public boolean isNewProgress() {
        return mProgressId == null;
    }
}
