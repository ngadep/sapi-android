package com.ngadep.fatteningcattle.users.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.main.MainActivity;
import com.ngadep.fatteningcattle.users.register.RegisterActivity;

public class LoginFragment extends Fragment implements LoginContract.View {

    private static final String TAG = "LoginFragment";
    private static final int REQUEST_REGISTER = 1;

    private LoginContract.Presenter mPresenter;
    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mSignInButton;
    private ProgressDialog mProgressDialog;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        Log.i(TAG, "Create new Instance Login Fragment");
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.login_frag, container, false);
        // Views
        mEmailField = (EditText) view.findViewById(R.id.field_email);
        mPasswordField = (EditText) view.findViewById(R.id.field_password);
        mSignInButton = (Button) view.findViewById(R.id.btn_sign_in);
        Button mRegister = (Button) view.findViewById(R.id.btn_login);

        // Click listeners
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToLogIn();
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivityForResult(intent, REQUEST_REGISTER);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult, request code: " + String.valueOf(requestCode) +
                ", result Code: " + String.valueOf(resultCode));
        if (REQUEST_REGISTER== requestCode && Activity.RESULT_OK == resultCode) {
            mPresenter.start();
        }
    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume");
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        Log.i(TAG, "setPresenter to " + presenter.getClass().getSimpleName());
        mPresenter = presenter;
    }

    @Override
    public void startMainActivity() {
        Log.i(TAG, "startMainActivity");
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }

    @Override
    public void showErrorText() {
        Log.i(TAG, "showErrorText");
        Snackbar.make(mSignInButton, "Login Failed", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showProgressDialog() {
        Log.i(TAG, "showProgressDialog");
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }

        mProgressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        Log.i(TAG, "hideProgressDialog");
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    private void tryToLogIn() {
        Log.i(TAG, "tryToLogIn");
        if (!validateForm()) {
            return;
        }

        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        mPresenter.tryToLogIn(email, password);
    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(mEmailField.getText().toString())) {
            mEmailField.setError("Required");
            result = false;
        } else {
            mEmailField.setError(null);
        }

        if (TextUtils.isEmpty(mPasswordField.getText().toString())) {
            mPasswordField.setError("Required");
            result = false;
        } else {
            mPasswordField.setError(null);
        }

        Log.i(TAG, "validateForm, result: " + String.valueOf(result));
        return result;
    }
}
