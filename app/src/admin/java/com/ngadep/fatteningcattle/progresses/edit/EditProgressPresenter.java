package com.ngadep.fatteningcattle.progresses.edit;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.models.Cow;
import com.ngadep.fatteningcattle.models.Progress;
import com.ngadep.fatteningcattle.progresses.ProgressRepository;

class EditProgressPresenter implements EditProgressContract.Presenter {

    private final String mCowId;
    private final String mProgressId;
    private final ProgressRepository mRepository;
    private final EditProgressContract.View mView;
    private Cow mCow;

    EditProgressPresenter(@Nullable String progressId, @NonNull String cowId,
                          @NonNull ProgressRepository repository, @NonNull EditProgressContract.View view) {
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
        } else {
            getCowModel();
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
        Progress progress = new Progress(mCowId, date, weight);
        if (isNewProgress()) {
            mRepository.saveProgress(progress);
            Cow cow = mCow;
            cow.setDate(date);
            cow.setWeight(weight);
            mRepository.updateCow(mCowId,cow);
        } else {
            mRepository.saveProgress(mProgressId, progress);
        }
        mView.showProgressList();
    }

    @Override
    public void cleanup() {
        mRepository.cleanup();
    }

    public boolean isNewProgress() {
        return mProgressId == null;
    }

    public void getCowModel() {
        mRepository.getCowModelFromId(mCowId, new BaseRepository.ModelListener<Cow>() {
            @Override
            public void onModelChange(Cow model) {
                mCow = model;
            }
        });
    }
}
