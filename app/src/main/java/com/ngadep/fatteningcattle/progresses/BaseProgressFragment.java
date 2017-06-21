package com.ngadep.fatteningcattle.progresses;

import android.support.v4.app.Fragment;

public abstract class BaseProgressFragment extends Fragment implements ProgressContract.View {

    protected ProgressContract.Presenter mPresenter;

    @Override
    public void setPresenter(ProgressContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
