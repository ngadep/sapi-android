package com.ngadep.fatteningcattle.progresses;

public class ProgressFragment extends BaseProgressFragment {
    public static ProgressFragment newInstance() {
        return new ProgressFragment();
    }

    @Override
    public void startEditProgressActivity(String cowId, String progressId) {
        // do nothing
    }

    @Override
    public void startEditCowActivity(String cowId, String packageId) {
        // do nothing
    }
}
