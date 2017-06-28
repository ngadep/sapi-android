package com.ngadep.fatteningcattle.packages;

import android.support.annotation.Nullable;

import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.models.User;
import com.ngadep.fatteningcattle.packages.PackageContract.View;

public class PackagePresenter implements PackageContract.Presenter {

    private String mUserId;
    private final View mView;
    private final PackageRepository mRepository;

    public PackagePresenter(@Nullable String userId, View view, PackageRepository repository) {
        mUserId = userId;
        mView = view;
        mRepository = repository;

        mView.setPresenter(this);
    }

    @Override
    public void getCurrentUserPackages() {
        mView.getPackages(mRepository.getPackagesFromUserId(mUserId));
    }

    @Override
    public void startCowActivity(String packageKey) {
        mView.startCowActivity(packageKey);
    }

    @Override
    public void showAddPackage() {
        mView.showAddPackageUi(mUserId);
    }

    @Override
    public void cleanup() {
        mRepository.cleanup();
    }

    @Override
    public void start() {
        if (mUserId == null) {
            mUserId = mRepository.getUid();
        } else {
            getUser();
        }

        getCurrentUserPackages();
    }

    public void getUser() {
        mRepository.getUserFromId(mUserId, new BaseRepository.ModelListener<User>() {
            @Override
            public void onModelChange(User model) {
                mView.notifyUserChange(model);
            }
        });
    }
}
