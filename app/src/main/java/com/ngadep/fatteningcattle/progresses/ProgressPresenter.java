package com.ngadep.fatteningcattle.progresses;

import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.models.Cow;

class ProgressPresenter implements ProgressContract.Presenter {

    private ProgressContract.View mView;
    private ProgressRepository mRepository;
    private String mCowId;
    private Long mPricePerKg;
    private Cow mCow;

    ProgressPresenter(ProgressContract.View view, ProgressRepository repository, String cowId) {
        this.mView = view;
        this.mRepository = repository;
        this.mCowId = cowId;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {
        queryCowModel();
        queryPricePerKg();
        mView.getAllCowProgress(mRepository.getCowProgressFromId(mCowId));
    }

    private void queryCowModel() {
        mRepository.getCowModelFromId(mCowId, new BaseRepository.ModelListener<Cow>() {
            @Override
            public void onModelChange(Cow model) {
                mCow = model;
                mView.notifyCowChange(mCow);
            }
        });
    }

    private void queryPricePerKg() {
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
    public void showEditProgressActivity(String progressId) {
        mView.startEditProgressActivity(mCowId, progressId);
    }

    @Override
    public void cleanup() {
        mRepository.cleanup();
    }

    @Override
    public void showEditProgressActivity() {
        mView.startEditProgressActivity(mCowId, null);
    }

    @Override
    public void showEditCowActivity() {
        mView.startEditCowActivity(mCowId, mCow.getPackage_id());
    }

    public Cow getCow() {
        return mCow;
    }
}
