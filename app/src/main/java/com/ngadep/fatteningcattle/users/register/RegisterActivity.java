package com.ngadep.fatteningcattle.users.register;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.users.UserRepository;
import com.ngadep.fatteningcattle.utils.ActivityUtils;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_act);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        setTitle(R.string.title_register);

        RegisterFragment registerFragment = (RegisterFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);

        if (registerFragment == null) {
            // Create the fragment
            registerFragment = RegisterFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), registerFragment, R.id.content_frame);
        }

        new RegisterPresenter(registerFragment, UserRepository.getInstance());
    }

    @Override
    public boolean onSupportNavigateUp() {
        Log.i(TAG, "onCreate");
        onBackPressed();
        return true;
    }}
