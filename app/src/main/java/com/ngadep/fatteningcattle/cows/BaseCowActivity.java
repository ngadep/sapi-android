package com.ngadep.fatteningcattle.cows;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.models.Package;
import com.ngadep.fatteningcattle.utils.ActivityUtils;

public class BaseCowActivity extends AppCompatActivity {

    public static final String EXTRA_PACKAGE_ID = "PACKAGE_ID";
    public static final String EXTRA_PACKAGE_MODEL = "PACKAGE_MODEL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cow_act);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        // Get the requested package id
        String packageId = getIntent().getStringExtra(EXTRA_PACKAGE_ID);
        Package pkg = getIntent().getParcelableExtra(EXTRA_PACKAGE_MODEL);

        setTitle(pkg.getName());

        CowFragment cowFragment = (CowFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);

        if (cowFragment == null) {
            // Create the fragment
            cowFragment = CowFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), cowFragment, R.id.content_frame);
        }

        new CowPresenter(cowFragment, CowRepository.getInstance(), packageId);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
