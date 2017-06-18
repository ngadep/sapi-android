package com.ngadep.fatteningcattle.cows;

public class CowPresenter implements CowContract.Presenter {
    private final CowContract.View mView;
    private final CowRepository mRepository;
    private final String mCowId;
    private Long mPricePerKg = 0L;

    public CowPresenter(CowContract.View view, CowRepository repository, String packageId) {
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
        mView.startEditCow();
    }

    @Override
    public void start() {
        queryPricePerKg();
        getPackageCows();
    }

    public void queryPricePerKg() {
        mRepository.getPricePerKg(new CowRepository.PriceListener() {
            @Override
            public void onPriceChange(Long price) {
                mPricePerKg = price;
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
}
