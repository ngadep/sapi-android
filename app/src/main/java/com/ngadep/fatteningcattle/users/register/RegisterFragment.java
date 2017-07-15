package com.ngadep.fatteningcattle.users.register;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.models.User;

public class RegisterFragment extends Fragment implements RegisterContract.View{

    private static final String TAG = "RegisterFragment";
    RegisterContract.Presenter mPresenter;

    EditText mUserName;
    EditText mEmail;
    EditText mPassword;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {
        Log.i(TAG, "newInstance");
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.register_frag, container, false);
        mUserName = (EditText) view.findViewById(R.id.ed_user_name);
        mEmail = (EditText) view.findViewById(R.id.ed_email);
        mPassword = (EditText) view.findViewById(R.id.ed_password);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_edit_user);
        fab.setImageResource(R.drawable.ic_done);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FormNotValid()) {
                    return;
                }

                String password = mPassword.getText().toString();
                mPresenter.saveUser(getUser(), password);
            }
        });

    }

    private User getUser() {
        return new User(mUserName.getText().toString(), mEmail.getText().toString());
    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume");
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        Log.i(TAG, "setPresenter to " + String.valueOf(presenter.getClass().getSimpleName()));
        mPresenter = presenter;
    }

    @Override
    public void registerSuccess() {
        Log.i(TAG, "registerSuccess");
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void showRegisterFailed() {
        Log.i(TAG, "showRegisterFailed");
        Snackbar.make(mUserName, getString(R.string.message_register_failed), Snackbar.LENGTH_LONG).show();
    }

    private boolean FormNotValid() {
        boolean result = true;
        if (TextUtils.isEmpty(mUserName.getText().toString())) {
            mUserName.setError("Required");
            result = false;
        } else {
            mUserName.setError(null);
        }

        if (TextUtils.isEmpty(mEmail.getText().toString())) {
            mEmail.setError("Required");
            result = false;
        } else {
            mEmail.setError(null);
        }

        if (TextUtils.isEmpty(mPassword.getText().toString())) {
            mPassword.setError("Required");
            result = false;
        } else {
            mPassword.setError(null);
        }

        Log.i(TAG, "FormNotValid, result: " + String.valueOf(result));
        return !result;
    }

}
