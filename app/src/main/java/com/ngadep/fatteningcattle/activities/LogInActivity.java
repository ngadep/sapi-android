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
import com.firebase.ui.auth.ResultCodes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.ngadep.fatteningcattle.BuildConfig;
import com.ngadep.fatteningcattle.R;

public class LogInActivity extends AppCompatActivity {

    private static final String TAG = "LogInActivity";
    private static final int RC_SIGN_IN = 69;

    private TextView mText;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        // for caching on local storage
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        mText = (TextView) findViewById(R.id.tx_message);
        mButton = (Button) findViewById(R.id.btn_sign_in);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trySignIn();
            }
        });

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        // Check auth on Activity start
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(LogInActivity.this, MainActivity.class));
            finish();
        } else {
            trySignIn();
        }
    }

    private void trySignIn() {
        startActivityForResult(AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(!BuildConfig.DEBUG)
                .setAllowNewEmailAccounts(false)
                .build(), RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            mButton.setVisibility(View.GONE);
            mText.setVisibility(View.GONE);
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == ResultCodes.OK) {
                startActivity(new Intent(LogInActivity.this, MainActivity.class));
                finish();
            } else {
                mText.setVisibility(View.VISIBLE);
                mButton.setVisibility(View.VISIBLE);

                if (response == null) {
                    Log.i(TAG, "sign in cancelled");
                    mText.setText(R.string.sign_in_cancelled);
                    return;
                }

                if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Log.w(TAG, "sign in no network");
                    mText.setText(R.string.sign_in_no_network);
                    return;
                }

                if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    Log.w(TAG, "sign in Unknown error");
                    mText.setText(R.string.sign_in_unknown_error);
                }
            }
        }
    }

}
