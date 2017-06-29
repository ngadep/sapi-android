package com.ngadep.fatteningcattle.cows;

public class CowFragment extends BaseCowFragment {

    public CowFragment() {
    }

    public static CowFragment newInstance() {
        return new CowFragment();
    }

    @Override
    public void startEditCowActivity(String packageId) {
        // do nothing
    }

    @Override
    public void startEditPackageActivity(String packageId, String userId) {
        // do nothing
    }
}
