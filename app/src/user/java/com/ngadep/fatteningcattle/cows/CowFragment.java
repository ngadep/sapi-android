package com.ngadep.fatteningcattle.cows;

import com.ngadep.fatteningcattle.models.Package;

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
    public void startEditPackageActivity(String packageId, Package packageModel) {
        // do nothing
    }
}
