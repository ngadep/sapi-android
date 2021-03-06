package com.ngadep.fatteningcattle.cows.edit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.cows.CowRepository;
import com.ngadep.fatteningcattle.utils.ActivityUtils;

public class EditCowActivity extends AppCompatActivity {

    private static final String TAG = "EditCowActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_cow_act);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        String packageId = getIntent().getStringExtra(EditCowFragment.ARGUMENT_EDIT_PACKAGE_ID);
        String cowId = getIntent().getStringExtra(EditCowFragment.ARGUMENT_EDIT_COW_ID);

        setToolBarTitle(cowId);

        EditCowFragment editCowFragment = (EditCowFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);

        if (editCowFragment == null) {
            // Create the fragment
            editCowFragment = EditCowFragment.newInstance();

            if (getIntent().hasExtra(EditCowFragment.ARGUMENT_EDIT_COW_ID)) {
                Bundle bundle = new Bundle();
                bundle.putString(EditCowFragment.ARGUMENT_EDIT_PACKAGE_ID, packageId);
                bundle.putString(EditCowFragment.ARGUMENT_EDIT_COW_ID, cowId);
                editCowFragment.setArguments(bundle);
            }

            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), editCowFragment, R.id.content_frame);
        }

        new EditCowPresenter(
                cowId,
                packageId,
                CowRepository.getInstance(),
                editCowFragment
        );
    }

    @Override
    public boolean onSupportNavigateUp() {
        Log.i(TAG, "onSupportNavigateUp");
        onBackPressed();
        return true;
    }

    public void setToolBarTitle(@Nullable String cowId) {
        Log.i(TAG, "setToolBarTitle");
        if(cowId == null) {
            setTitle(R.string.cow_add);
        } else {
            setTitle(R.string.cow_edit);
        }
    }
}
