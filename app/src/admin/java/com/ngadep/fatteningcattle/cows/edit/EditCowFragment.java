package com.ngadep.fatteningcattle.cows.edit;

import android.support.v4.app.Fragment;

public class EditCowFragment extends Fragment implements EditCowContract.View {
    public static final String ARGUMENT_EDIT_COW_ID = "EDIT_COW_ID";
    public static final String ARGUMENT_EDIT_COW_MODEL = "EDIT_COW_MODEL";

    private EditCowContract.Presenter mPresenter;

    @Override
    public void setPresenter(EditCowContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public static EditCowFragment newInstance() {
        return new EditCowFragment();
    }
}
