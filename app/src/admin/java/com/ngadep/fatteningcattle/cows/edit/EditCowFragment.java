package com.ngadep.fatteningcattle.cows.edit;

import android.support.v4.app.Fragment;

public class EditCowFragment extends Fragment implements EditCowContract.View {
    private EditCowContract.Presenter mPresenter;

    @Override
    public void setPresenter(EditCowContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
