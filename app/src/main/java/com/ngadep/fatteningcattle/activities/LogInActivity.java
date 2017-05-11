package com.ngadep.fatteningcattle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.database.FirebaseDatabase;
import com.ngadep.fatteningcattle.BuildConfig;
import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.contracts.LoginContract;
import com.ngadep.fatteningcattle.presenter.LoginPresenter;
import com.ngadep.fatteningcattle.repositories.LoginRepository;

public class LogInActivity extends AppCompatActivity
        implements LoginContract.View {

    private static final String TAG = "LogInActivity";
    private static final int RC_SIGN_IN = 69;

    private TextView mText;
    private Button mButton;
    LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        // for caching on local storage
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        mText = (TextView) findViewById(R.id.tx_message);
        mButton = (Button) findViewById(R.id.btn_sign_in);

        LoginContract.Repository mRepository = LoginRepository.getInstance();
        mPresenter = new LoginPresenter(this, mRepository);
        mPresenter.start();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.tryToLogIn();
            }
        });
    }

    @Override
    public void showTextAndButton(Boolean visible) {
        if (visible) {
            mButton.setVisibility(View.VISIBLE);
            mText.setVisibility(View.VISIBLE);
        } else {
            mButton.setVisibility(View.GONE);
            mText.setVisibility(View.GONE);
        }
    }

    @Override
    public void tryLogIn() {
        startActivityForResult(AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(!BuildConfig.DEBUG)
                .setAllowNewEmailAccounts(false)
                .build(), RC_SIGN_IN);
    }

    @Override
    public void startMainActivity() {
        startActivity(new Intent(LogInActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void showErrorText(int resId) {
        Log.i(TAG, getResources().getString(resId));
        mText.setText(resId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            int result = LoginPresenter.LogInStatus.CANCELLED;

            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == LoginPresenter.LogInStatus.SUCCESS) {
                result = resultCode;
            } else {
                if (response == null) {
                    result = LoginPresenter.LogInStatus.CANCELLED;
                } else if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                    result = LoginPresenter.LogInStatus.NO_NETWORK;
                } else if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    result = LoginPresenter.LogInStatus.UNKNOWN_ERROR;
                }
            }
            mPresenter.onLoginResult(result);
        }
    }
}
