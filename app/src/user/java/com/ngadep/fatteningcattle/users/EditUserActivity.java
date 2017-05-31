package com.ngadep.fatteningcattle.users;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.data.models.User;
import com.ngadep.fatteningcattle.utils.ActivityUtils;

public class EditUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        // Get the requested user model
        User user = getIntent().getParcelableExtra(EditUserFragment.EXTRA_USER_MODEL);
        String userId = getIntent().getStringExtra(EditUserFragment.EXTRA_USER_ID);

        if (user != null) {
            setTitle(user.getUserName());
        }

        EditUserFragment editUserFragment = (EditUserFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);

        if (editUserFragment == null) {
            // Create the fragment
            editUserFragment = EditUserFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), editUserFragment, R.id.content_frame);
        }

        new EditUserPresenter(userId, user, editUserFragment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }}
