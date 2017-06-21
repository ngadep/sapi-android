package com.ngadep.fatteningcattle.cows;

import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.models.Package;

public class CowPresenter implements CowContract.Presenter {
    private final CowContract.View mView;
    private final CowRepository mRepository;
    private final String mPackageId;
    private final Package mPackageModel;
    private Long mPricePerKg = 0L;

    public CowPresenter(CowContract.View view, CowRepository repository, String packageId, Package pkg) {
        mView = view;
        mRepository = repository;
        mPackageId = packageId;
        mPackageModel = pkg;
        mView.setPresenter(this);
    }

    public void getPackageCows() {
        mView.getAllPackageCow(mRepository.getPackageCowFromId(mPackageId));
    }

    @Override
    public void startCowProgress(String cowId) {
        mView.startCowProgressActivity(cowId);
    }

    @Override
    public void startAddCowUi() {
        mView.startEditCow(mPackageId);
    }

    @Override
    public void start() {
        queryPricePerKg();
        getPackageCows();
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
    public void showEditPackageUi() {
        mView.showEditPackage(mPackageId, mPackageModel);
    }
}
