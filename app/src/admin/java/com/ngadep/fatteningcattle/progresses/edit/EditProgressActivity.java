package com.ngadep.fatteningcattle.progresses.edit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.progresses.ProgressRepository;
import com.ngadep.fatteningcattle.utils.ActivityUtils;

public class EditProgressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_progress_act);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        String cowId = getIntent().getStringExtra(EditProgressFragment.ARGUMENT_EDIT_COW_ID);
        String progressId = getIntent().getStringExtra(EditProgressFragment.ARGUMENT_EDIT_PROGRESS_ID);

        setToolBarTitle(progressId);

        EditProgressFragment editProgressFragment = (EditProgressFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);

        if (editProgressFragment == null) {
            // Create the fragment
            editProgressFragment = EditProgressFragment.newInstance();

            if (getIntent().hasExtra(EditProgressFragment.ARGUMENT_EDIT_COW_ID)) {
                Bundle bundle = new Bundle();
                bundle.putString(EditProgressFragment.ARGUMENT_EDIT_COW_ID, cowId);
                bundle.putString(EditProgressFragment.ARGUMENT_EDIT_PROGRESS_ID, progressId);
                editProgressFragment.setArguments(bundle);
            }

            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), editProgressFragment, R.id.content_frame);
        }

        new EditProgressPresenter(
                cowId,
                progressId,
                ProgressRepository.getInstance(),
                editProgressFragment
        );
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void setToolBarTitle(@Nullable String packageId) {
        if(packageId == null) {
            setTitle(R.string.progress_add);
        } else {
            setTitle(R.string.progress_edit);
        }
    }
}
