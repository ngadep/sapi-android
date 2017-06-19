package com.ngadep.fatteningcattle.cows;

import android.content.Intent;

import com.ngadep.fatteningcattle.cows.edit.EditCowActivity;
import com.ngadep.fatteningcattle.cows.edit.EditCowFragment;

public class CowFragment extends BaseCowFragment {

    private static final int REQUEST_ADD_COW = 1;

    public CowFragment() {
    }

    public static CowFragment newInstance() {
        return new CowFragment();
    }

    @Override
    public void startEditCow(String packageId) {
        Intent intent = new Intent(getActivity(), EditCowActivity.class);
        intent.putExtra(EditCowFragment.ARGUMENT_EDIT_PACKAGE_ID, packageId);
        startActivityForResult(intent, REQUEST_ADD_COW);
    }
}
