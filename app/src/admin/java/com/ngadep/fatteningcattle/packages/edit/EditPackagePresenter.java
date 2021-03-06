package com.ngadep.fatteningcattle.packages.edit;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.models.Package;
import com.ngadep.fatteningcattle.packages.PackageRepository;

class EditPackagePresenter implements EditPackageContract.Presenter {

    private final String mPackageId;
    private Package mPackage, mEditPackage;
    private final PackageRepository mRepository;
    private final EditPackageContract.View mView;
    private final String mUserId;

    EditPackagePresenter(@Nullable String packageId, @NonNull String userId,
                         @NonNull PackageRepository repository,
                         @NonNull EditPackageContract.View view) {
        mPackageId = packageId;
        mUserId = userId;
        mRepository = repository;
        mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void start() {
        if (!isNewPackage()) {
            populatePackage();
        }
    }

    @Override
    public void populatePackage() {
        mRepository.getPackageFromId(mPackageId, new BaseRepository.ModelListener<Package>() {
            @Override
            public void onModelChange(Package model) {
                mPackage = model;
                if (mView.isActive()) {
                    mView.setName(mPackage.getName());
                    mView.setLocation(mPackage.getLocation());
                    mView.setType(mPackage.getType());
                    mView.setActive(mPackage.isActive());
                }
            }
        });
    }

    @Override
    public void savePackage(String name, String location, int type, boolean active) {
        mEditPackage = new Package(mUserId, name, location, type, active);
        if (isNewPackage()) {
            mRepository.savePackage(mEditPackage);
        } else {
            mRepository.savePackage(mPackageId, mEditPackage);
        }
        mView.showPackageList();
    }

    @Override
    public void cleanup() {
        mRepository.cleanup();
    }

    @Override
    public boolean isNewPackage() {
        return mPackageId == null;
    }

    Package getPackage() {
        return mPackage;
    }

    Package getEditPackage() {
        return mEditPackage;
    }
}
