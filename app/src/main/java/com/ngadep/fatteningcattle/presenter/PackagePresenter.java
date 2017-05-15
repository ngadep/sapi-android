package com.ngadep.fatteningcattle.presenter;

import com.ngadep.fatteningcattle.contracts.PackageContract.View;
import com.ngadep.fatteningcattle.contracts.PackageContract.Repository;

public class PackagePresenter {

    private final View mView;
    private final Repository mRepository;

    public PackagePresenter(View view, Repository repository) {
        mView = view;
        mRepository = repository;
    }

    public void getUserPackages() {
        mView.getPackages(mRepository.getPackagesFromCurrentUser());
    }

    public void startCowActivity(String packageKey, String packageName) {
        mView.startCowActivity(packageKey, packageName);
    }
}
