package com.ngadep.fatteningcattle.packages;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.utils.ActivityUtils;

public class PackageActivity extends AppCompatActivity {

    public static final String EXTRA_USER_ID = "extra user id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.package_act);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        // Get the requested user model
        String userId = getIntent().getStringExtra(EXTRA_USER_ID);

        PackageFragmentAdmin packageFragment = (PackageFragmentAdmin) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);

        if (packageFragment == null) {
            // Create the fragment
            packageFragment = PackageFragmentAdmin.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), packageFragment, R.id.content_frame);
        }

        new PackagePresenter(userId, packageFragment, PackageRepository.getInstance());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
