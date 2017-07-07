package com.ngadep.fatteningcattle.packages.edit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.packages.PackageRepository;
import com.ngadep.fatteningcattle.utils.ActivityUtils;

public class EditPackageActivity extends AppCompatActivity {

    private static final String TAG = "EditPackageActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_package_act);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        String userId = getIntent().getStringExtra(EditPackageFragment.ARGUMENT_EDIT_USER_ID);
        String packageId = getIntent().getStringExtra(EditPackageFragment.ARGUMENT_EDIT_PACKAGE_ID);

        setToolBarTitle(packageId);

        EditPackageFragment editPackageFragment = (EditPackageFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);

        if (editPackageFragment == null) {
            // Create the fragment
            editPackageFragment = EditPackageFragment.newInstance();

            if (getIntent().hasExtra(EditPackageFragment.ARGUMENT_EDIT_PACKAGE_ID)) {
                Bundle bundle = new Bundle();
                bundle.putString(EditPackageFragment.ARGUMENT_EDIT_USER_ID, userId);
                bundle.putString(EditPackageFragment.ARGUMENT_EDIT_PACKAGE_ID, packageId);
                editPackageFragment.setArguments(bundle);
            }

            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), editPackageFragment, R.id.content_frame);
        }

        new EditPackagePresenter(
                packageId,
                userId,
                PackageRepository.getInstance(),
                editPackageFragment
        );
    }

    @Override
    public boolean onSupportNavigateUp() {
        Log.i(TAG, "onSupportNavigateUp");
        onBackPressed();
        return true;
    }

    public void setToolBarTitle(@Nullable String packageId) {
        Log.i(TAG, "setToolBarTitle");
        if(packageId == null) {
            setTitle(R.string.package_add);
        } else {
            setTitle(R.string.package_edit);
        }
    }
}
