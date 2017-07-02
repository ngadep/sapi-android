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
    private Progress mProgress, mProgressEdit;
    private Cow mCow, mCowEdit;

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
                mProgress = model;
                if (mView.isActive()) {
                    mView.setWeight(model.getWeight());
                    mView.setDate(model.getDate());
                }
            }
        });
    }

    @Override
    public void saveProgress(int weight, long date) {
        mProgressEdit = new Progress(mCowId, date, weight);
        if (isNewProgress()) {
            mRepository.saveProgress(mProgressEdit);
            mCowEdit = mCow;
            mCowEdit.setDate(date);
            mCowEdit.setWeight(weight);
            mRepository.updateCow(mCowId, mCowEdit);
        } else {
            mRepository.saveProgress(mProgressId, mProgressEdit);
        }
        mView.showProgressList();
    }

    @Override
    public void cleanup() {
        mRepository.cleanup();
    }

    boolean isNewProgress() {
        return mProgressId == null;
    }

    private void getCowModel() {
        mRepository.getCowModelFromId(mCowId, new BaseRepository.ModelListener<Cow>() {
            @Override
            public void onModelChange(Cow model) {
                mCow = model;
            }
        });
    }

    Progress getProgress() {
        return mProgress;
    }

    Progress getProgressEdit() {
        return mProgressEdit;
    }

    Cow getCow() {
        return mCow;
    }

    Cow getCowEdit() {
        return mCowEdit;
    }
}
