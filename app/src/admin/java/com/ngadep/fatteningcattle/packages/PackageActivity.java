package com.ngadep.fatteningcattle.packages;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.data.models.User;
import com.ngadep.fatteningcattle.utils.ActivityUtils;

public class PackageActivity extends AppCompatActivity {

    public static final String EXTRA_USER_MODEL = "extra user model";
    public static final String EXTRA_USER_ID = "extra user id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        // Get the requested user model
        User user = getIntent().getParcelableExtra(EXTRA_USER_MODEL);
        String userId = getIntent().getStringExtra(EXTRA_USER_ID);

        setTitle(user.getDisplayName());
        PackageFragment packageFragment = (PackageFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);

        if (packageFragment == null) {
            // Create the fragment
            packageFragment = PackageFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), packageFragment, R.id.content_frame);
        }

        new PackagePresenter(userId, packageFragment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
