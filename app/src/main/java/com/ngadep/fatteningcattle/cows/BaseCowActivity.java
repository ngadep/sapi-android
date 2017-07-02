package com.ngadep.fatteningcattle.cows;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.utils.ActivityUtils;

@SuppressLint("Registered")
public class BaseCowActivity extends AppCompatActivity {

    public static final String EXTRA_PACKAGE_ID = "PACKAGE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cow_act);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        // Get the requested package id
        String packageId = getIntent().getStringExtra(EXTRA_PACKAGE_ID);

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
