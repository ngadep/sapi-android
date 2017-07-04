package com.ngadep.fatteningcattle.users.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.users.UserRepository;
import com.ngadep.fatteningcattle.utils.ActivityUtils;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate Login Activity");

        setContentView(R.layout.login_act);

        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);

        if (loginFragment == null) {
            // Create the fragment
            loginFragment = LoginFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), loginFragment, R.id.content_frame);
        }

        new LoginPresenter(loginFragment, UserRepository.getInstance());
    }

}