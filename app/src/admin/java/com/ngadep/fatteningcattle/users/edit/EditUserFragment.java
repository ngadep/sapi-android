package com.ngadep.fatteningcattle.users.edit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ngadep.fatteningcattle.R;

public class EditUserFragment extends Fragment implements EditUserContract.View{

    EditUserContract.Presenter mPresenter;

    public EditUserFragment() {
        // Required empty public constructor
    }

    public static EditUserFragment newInstance() {
        EditUserFragment fragment = new EditUserFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_user, container, false);
    }

    @Override
    public void setPresenter(EditUserContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
