package com.ngadep.fatteningcattle.presenter;

import com.ngadep.fatteningcattle.contracts.PackageContract.View;
import com.ngadep.fatteningcattle.contracts.PackageContract.Repository;

public class PackagePresenter {

    private View mView;
    private Repository mRepository;

    public PackagePresenter(View view, Repository repository) {
        mView = view;
        mRepository = repository;
    }

    public void getUserPackages() {
        mView.getPackages(mRepository.getPackagesFromCurrentUser());
    }
}
