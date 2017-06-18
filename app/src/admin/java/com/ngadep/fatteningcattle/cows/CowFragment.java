package com.ngadep.fatteningcattle.cows;

import android.content.Intent;

import com.ngadep.fatteningcattle.cows.edit.EditCowActivity;

public class CowFragment extends BaseCowFragment {

    private static final int REQUEST_ADD_COW = 1;

    public CowFragment() {
    }

    public static CowFragment newInstance() {
        return new CowFragment();
    }

    @Override
    public void startEditCow() {
        Intent intent = new Intent(getActivity(), EditCowActivity.class);
        startActivityForResult(intent, REQUEST_ADD_COW);
    }
}
