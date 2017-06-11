package com.ngadep.fatteningcattle.packages.edit;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ngadep.fatteningcattle.models.Package;
import com.ngadep.fatteningcattle.packages.PackageRepository;

public class EditPackagePresenter implements EditPackageContract.Presenter {

    private final String mPackageId;
    private final Package mPackageModel;
    private final PackageRepository mRepository;
    private final EditPackageContract.View mView;
    private final String mUserId;

    public EditPackagePresenter(@NonNull String userId, @Nullable String packageId,
                                @Nullable Package packageModel, @NonNull PackageRepository repository,
                                @NonNull EditPackageContract.View view) {
        mPackageId = packageId;
        mPackageModel = packageModel;
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
        if (mView.isActive()) {
            mView.setName(mPackageModel.getName());
            mView.setLocation(mPackageModel.getLocation());
            mView.setType(mPackageModel.getType());
            mView.setActive(mPackageModel.isActive());
        }
    }

    @Override
    public void savePackage(String name, String location, int type, boolean active) {
        Package pkg = new Package(mUserId, name, location, type, active);
        if (isNewPackage()) {
            mRepository.savePackage(pkg);
        } else {
            mRepository.savePackage(mPackageId, pkg);
        }
        mView.showPackageList();
    }

    @Override
    public boolean isNewPackage() {
        return mPackageId != null;
    }
}
