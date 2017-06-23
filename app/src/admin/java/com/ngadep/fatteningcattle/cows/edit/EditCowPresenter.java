package com.ngadep.fatteningcattle.cows.edit;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.cows.CowRepository;
import com.ngadep.fatteningcattle.models.Cow;

public class EditCowPresenter implements EditCowContract.Presenter {
    private final String mPackageId;
    private final String mCowId;
    private Cow mCow;
    private final CowRepository mRepository;
    private final EditCowContract.View mView;

    public EditCowPresenter(@NonNull String packageId, @Nullable String cowId,
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
        Cow cow = new Cow(mPackageId, earTag, sex, weight, date);
        if (isNewCow()) {
            mRepository.saveCow(cow);
        } else {
            mRepository.saveCow(mCowId, cow);
        }
        mView.showCowList();

    }

    @Override
    public void cleanup() {
        mRepository.cleanup();
    }

    public boolean isNewCow() {
        return mCowId == null;
    }
}
