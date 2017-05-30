package com.ngadep.fatteningcattle.users;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.data.models.User;

public class EditUserFragment extends Fragment implements EditUserContract.View{

    public static final String EXTRA_USER_MODEL = "USER_MODEL";
    public static final String EXTRA_USER_ID = "USER_ID";
    EditUserContract.Presenter mPresenter;

    EditText mUserName;
    EditText mEmail;
    EditText mPassword;

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
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_edit_user, container, false);
        mUserName = (EditText) view.findViewById(R.id.ed_user_name);
        mEmail = (EditText) view.findViewById(R.id.ed_email);
        mPassword = (EditText) view.findViewById(R.id.ed_password);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_edit_user);
        fab.setImageResource(R.drawable.ic_done);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.saveUser(getUser(), mPassword.getText().toString());
            }
        });

    }

    private User getUser() {
        return new User(mUserName.getText().toString(), mEmail.getText().toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(EditUserContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showUser(User user) {
        mUserName.setText(user.getUserName());
        mEmail.setText(user.getEmail());
    }
}
