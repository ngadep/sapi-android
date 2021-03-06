package com.ngadep.fatteningcattle.progresses;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.utils.ActivityUtils;

public class ProgressActivity extends AppCompatActivity {

    public static final String EXTRA_COW_ID = "PROGRESS_ID";
    private static final String TAG = "ProgressActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_act);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        // Get the requested package id
        String cowId = getIntent().getStringExtra(EXTRA_COW_ID);

        ProgressFragment progressFragment = (ProgressFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);

        if (progressFragment == null) {
            // Create the fragment
            progressFragment = ProgressFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), progressFragment, R.id.content_frame);
        }

        new ProgressPresenter(progressFragment, ProgressRepository.getInstance(), cowId);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Log.i(TAG, "onSupportNavigateUp");
        onBackPressed();
        return true;
    }}
