package com.ngadep.fatteningcattle.cows;

public class CowPresenter implements CowContract.Presenter {
    private final CowContract.View mView;
    private final CowContract.Repository mRepository;
    private final String mCowId;

    public CowPresenter(CowContract.View view, CowContract.Repository repository, String packageId) {
        mView = view;
        mRepository = repository;
        mCowId = packageId;
        mView.setPresenter(this);
    }

    public void getPackageCows() {
        mView.getAllPackageCow(mRepository.getPackageCowFromId(mCowId));
    }

    public void startCowDetailActivity(String cowId) {
        /* TODO: show Cow Detail Activity */
    }

    @Override
    public void startAddCowUi() {
        /* TODO: Add Cow Detail Activity */
    }

    @Override
    public void start() {
        getPackageCows();
    }
}
