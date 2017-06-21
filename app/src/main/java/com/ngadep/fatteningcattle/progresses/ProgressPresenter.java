package com.ngadep.fatteningcattle.progresses;

import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.models.Cow;

public class ProgressPresenter implements ProgressContract.Presenter {

    private ProgressContract.View mView;
    private ProgressRepository mRepository;
    private String mCowId;
    private Cow mCowModel;
    private Long mPricePerKg;

    public ProgressPresenter(ProgressContract.View view, ProgressRepository repository, String cowId) {
        this.mView = view;
        this.mRepository = repository;
        this.mCowId = cowId;
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
                mCowModel = model;
                mView.notifyCowChange(model);
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
        //TODO: start ProgressDetailActivity
    }

    @Override
    public void cleanup() {
        mRepository.cleanup();
    }
}
