package com.ngadep.fatteningcattle.cows.edit;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ngadep.fatteningcattle.cows.CowRepository;
import com.ngadep.fatteningcattle.models.Cow;

public class EditCowPresenter implements EditCowContract.Presenter {
    private final String mPackageId;
    private final String mCowId;
    private final Cow mCowModel;
    private final CowRepository mRepository;
    private final EditCowContract.View mView;

    public EditCowPresenter(@NonNull String packageId, @Nullable String cowId, @Nullable Cow cowModel,
                            @NonNull CowRepository repository, @NonNull EditCowContract.View view) {
        mPackageId = packageId;
        mCowId = cowId;
        mCowModel = cowModel;
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
        if (mView.isActive()) {
            mView.setEarTag(mCowModel.getEar_tag());
            mView.setSex(mCowModel.getSex());
            mView.setWeight(mCowModel.getWeight());
            mView.setDate(mCowModel.getDate());
        }
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

    public boolean isNewCow() {
        return mCowId == null;
    }
}
