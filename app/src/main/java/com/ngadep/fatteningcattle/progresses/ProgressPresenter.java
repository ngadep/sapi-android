package com.ngadep.fatteningcattle.progresses;

import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.models.Cow;

public class ProgressPresenter implements ProgressContract.Presenter {

    private ProgressContract.View mView;
    private ProgressRepository mRepository;
    private String mCowId;
    private Long mPricePerKg;
    private Cow mCow;

    public ProgressPresenter(ProgressContract.View view, ProgressRepository repository, String cowId) {
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
    public void startProgressDetailActivity(String progressId) {
        mView.showEditProgressActivity(mCowId, progressId);
    }

    @Override
    public void cleanup() {
        mRepository.cleanup();
    }

    @Override
    public void startAddProgressUi() {
        mView.showEditProgressActivity(mCowId, null);
    }

    @Override
    public void showEditCowUi() {
        mView.showEditCowActivity(mCowId, mCow.getPackage_id());
    }
}
