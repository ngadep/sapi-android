package com.ngadep.fatteningcattle.users.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.main.MainActivity;

public class LogInActivity extends AppCompatActivity implements LoginContract.View {

    private static final String TAG = "SignInActivity";

    private LoginContract.Presenter mPresenter;
    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mSignInButton;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginRepository mRepository = LoginRepository.getInstance();
        mPresenter = new LoginPresenter(this, mRepository);

        // Views
        mEmailField = (EditText) findViewById(R.id.field_email);
        mPasswordField = (EditText) findViewById(R.id.field_password);
        mSignInButton = (Button) findViewById(R.id.button_sign_in);

        // Click listeners
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToLogIn();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "on start");
        mPresenter.start();
    }

    private void tryToLogIn() {
        Log.d(TAG, "signIn");
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

        return result;
    }

    @Override
    public void startMainActivity() {
        // Go to MainActivity
        startActivity(new Intent(LogInActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void showErrorText() {
        Snackbar.make(mSignInButton, "Login Failed", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }

        mProgressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }
}