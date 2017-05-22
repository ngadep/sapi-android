package com.ngadep.fatteningcattle.packages;

import com.ngadep.fatteningcattle.packages.PackageContract.View;
import com.ngadep.fatteningcattle.packages.PackageContract.Repository;

class PackagePresenter {

    private final View mView;
    private final Repository mRepository;

    PackagePresenter(View view, Repository repository) {
        mView = view;
        mRepository = repository;
    }

    void getCurrentUserPackages() {
        mView.getPackages(mRepository.getPackagesFromCurrentUser());
    }

    void startCowActivity(String packageKey, String packageName) {
        mView.startCowActivity(packageKey, packageName);
    }
}
