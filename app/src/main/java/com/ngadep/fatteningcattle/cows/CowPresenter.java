package com.ngadep.fatteningcattle.cows;

public class CowPresenter {
    private final CowContract.View mView;
    private final CowContract.Repository mRepository;
    private final String mCowId;

    public CowPresenter(CowContract.View view, CowContract.Repository repository, String packageId) {
        mView = view;
        mRepository = repository;
        mCowId = packageId;
    }

    public void getPackageCows() {
        mView.getAllPackageCow(mRepository.getPackageCowFromId(mCowId));
    }

    public void startCowDetailActivity(String cowId) {
        /* TODO: Add Cow Detail Activity */
    }
}
