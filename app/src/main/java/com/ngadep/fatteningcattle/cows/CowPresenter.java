package com.ngadep.fatteningcattle.cows;

import com.google.firebase.storage.StorageReference;
import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.models.Package;

public class CowPresenter implements CowContract.Presenter {
    private final CowContract.View mView;
    private final CowRepository mRepository;
    private final String mPackageId;
    private Package mPackage;
    private Long mPricePerKg = 0L;

    public CowPresenter(CowContract.View view, CowRepository repository, String packageId) {
        mView = view;
        mRepository = repository;
        mPackageId = packageId;
        mView.setPresenter(this);
    }

    public void getPackageCows() {
        mView.getAllPackageCow(mRepository.getPackageCowFromId(mPackageId));
    }

    @Override
    public void showProgressActivity(String cowId) {
        mView.startProgressActivity(cowId);
    }

    @Override
    public void showEditCowActivity() {
        mView.startEditCowActivity(mPackageId);
    }

    @Override
    public void start() {
        queryPackage();
        queryPricePerKg();
        getPackageCows();
    }

    private void queryPackage() {
        mRepository.getPackageFromId(mPackageId, new BaseRepository.ModelListener<Package>() {
            @Override
            public void onModelChange(Package model) {
                mPackage = model;
                mView.notifyPackageChange(mPackage);
            }
        });
    }

    public void queryPricePerKg() {
        mRepository.getSetting("price", new BaseRepository.SettingListener<Long>() {
            @Override
            public void onValueChange(Long value) {
                mPricePerKg = value;
                mView.notifyPriceChange();
            }
        });
    }

    @Override
    public Long getPricePerKg() {
        return mPricePerKg;
    }

    @Override
    public void cleanup() {
        mRepository.cleanup();
    }

    @Override
    public void showEditPackageActivity() {
        mView.startEditPackageActivity(mPackageId, mPackage);
    }

    @Override
    public StorageReference getCowImage(String cowId) {
        return mRepository.getCowImageFromId(cowId);
    }

    public Package getPackage() {
        return mPackage;
    }
}
