package com.ngadep.fatteningcattle.cows.edit;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.cows.CowRepository;
import com.ngadep.fatteningcattle.models.Cow;

class EditCowPresenter implements EditCowContract.Presenter {
    private final String mPackageId;
    private final String mCowId;
    private Cow mCow, mCowEdit;
    private final CowRepository mRepository;
    private final EditCowContract.View mView;

    EditCowPresenter(@Nullable String cowId, @NonNull String packageId,
                     @NonNull CowRepository repository, @NonNull EditCowContract.View view) {
        mPackageId = packageId;
        mCowId = cowId;
        mRepository = repository;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        if (!isNewCow()) {
            populateCow();
        }
    }

    private void populateCow() {
        mRepository.getCowFromId(mCowId, new BaseRepository.ModelListener<Cow>() {
            @Override
            public void onModelChange(Cow model) {
                mCow = model;
                if (mView.isActive()) {
                    mView.setEarTag(mCow.getEar_tag());
                    mView.setSex(mCow.getSex());
                    mView.setWeight(mCow.getWeight());
                    mView.setDate(mCow.getDate());
                    mView.hideWeightAndDate();
                }
            }
        });
    }

    @Override
    public void saveCow(String earTag, String sex, int weight, long date) {
        mCowEdit = new Cow(mPackageId, earTag, sex, weight, date);
        if (isNewCow()) {
            mRepository.saveCow(mCowEdit);
        } else {
            mRepository.saveCow(mCowId, mCowEdit);
        }
        mView.showCowList();

    }

    @Override
    public void cleanup() {
        mRepository.cleanup();
    }

    @Override
    public boolean isNewCow() {
        return mCowId == null;
    }

    public Cow getCow() {
        return mCow;
    }

    public Cow getCowEdit() {
        return mCowEdit;
    }
}
