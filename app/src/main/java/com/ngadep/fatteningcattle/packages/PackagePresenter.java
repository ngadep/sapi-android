package com.ngadep.fatteningcattle.packages;

import android.support.annotation.Nullable;

import com.ngadep.fatteningcattle.packages.PackageContract.View;

public class PackagePresenter implements PackageContract.Presenter {

    private String mUserId;
    private final View mView;
    private final PackageRepository mRepository;

    public PackagePresenter(@Nullable String userId, View view) {
        mUserId = userId;
        mView = view;
        mRepository = PackageRepository.getInstance();

        mView.setPresenter(this);
    }

    @Override
    public void getCurrentUserPackages() {
        if (mUserId == null) {
            mUserId = mRepository.getUid();
        }

        mView.getPackages(mRepository.getPackagesFromUserId(mUserId));
    }

    @Override
    public void startCowActivity(String packageKey, String packageName) {
        mView.startCowActivity(packageKey, packageName);
    }

    @Override
    public void start() {
        getCurrentUserPackages();
    }
}
