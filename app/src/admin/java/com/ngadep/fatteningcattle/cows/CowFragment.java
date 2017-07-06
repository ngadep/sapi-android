package com.ngadep.fatteningcattle.cows;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.cows.edit.EditCowActivity;
import com.ngadep.fatteningcattle.cows.edit.EditCowFragment;
import com.ngadep.fatteningcattle.packages.edit.EditPackageActivity;
import com.ngadep.fatteningcattle.packages.edit.EditPackageFragment;

public class CowFragment extends BaseCowFragment {

    private static final int REQUEST_ADD_COW = 1;
    private static final int REQUEST_EDIT_PACKAGE = 2;
    private static final String TAG = "CowFragment";

    public CowFragment() {
    }

    public static CowFragment newInstance() {
        Log.i(TAG, "newInstance");
        return new CowFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "onActivityResult, requestCode: " + String.valueOf(requestCode) +
                ", resultCode: " + String.valueOf(resultCode));
        super.onActivityResult(requestCode, resultCode, data);
        if (Activity.RESULT_OK == resultCode) {
            if (REQUEST_ADD_COW == requestCode) {
                showMessage(getString(R.string.cow_message_successfully_saved));
            } else if (REQUEST_EDIT_PACKAGE == requestCode) {
                showMessage(getString(R.string.package_message_successfully_saved));
            }
        }
    }

    @Override
    public void startEditCowActivity(String packageId) {
        Log.i(TAG, "startEditCowActivity");
        Intent intent = new Intent(getActivity(), EditCowActivity.class);
        intent.putExtra(EditCowFragment.ARGUMENT_EDIT_PACKAGE_ID, packageId);
        startActivityForResult(intent, REQUEST_ADD_COW);
    }

    @Override
    public void startEditPackageActivity(String packageId, String userId) {
        Log.i(TAG, "startEditPackageActivity");
        Intent intent = new Intent(getActivity(), EditPackageActivity.class);
        intent.putExtra(EditPackageFragment.ARGUMENT_EDIT_USER_ID, userId);
        intent.putExtra(EditPackageFragment.ARGUMENT_EDIT_PACKAGE_ID, packageId);
        startActivityForResult(intent, REQUEST_EDIT_PACKAGE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.i(TAG, "onCreateOptionMenu");
        inflater.inflate(R.menu.menu_cows, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(TAG, "onOptionsItemSelected, itemId: " + String.valueOf(item.getItemId()));
        switch (item.getItemId()) {
            case R.id.action_edit_package:
                mPresenter.showEditPackageActivity();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
