package com.ngadep.fatteningcattle.users.register;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.data.models.User;
import com.ngadep.fatteningcattle.utils.ActivityUtils;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        // Get the requested user model
        User user = getIntent().getParcelableExtra(RegisterFragment.EXTRA_USER_MODEL);
        String userId = getIntent().getStringExtra(RegisterFragment.EXTRA_USER_ID);

        if (user != null) {
            setTitle(user.getUserName());
        }

        RegisterFragment registerFragment = (RegisterFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);

        if (registerFragment == null) {
            // Create the fragment
            registerFragment = RegisterFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), registerFragment, R.id.content_frame);
        }

        new RegisterPresenter(userId, user, registerFragment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }}
