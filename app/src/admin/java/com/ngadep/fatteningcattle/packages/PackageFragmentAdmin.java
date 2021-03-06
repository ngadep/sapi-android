package com.ngadep.fatteningcattle.packages;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.packages.edit.EditPackageActivity;
import com.ngadep.fatteningcattle.packages.edit.EditPackageFragment;

public class PackageFragmentAdmin extends PackageFragment {
    private static final int REQUEST_ADD_PACKAGE = 1;
    private static final String TAG = "PackageFragmentAdmin";

    public PackageFragmentAdmin() {
    }

    public static PackageFragmentAdmin newInstance() {
        Log.i(TAG, "newInstance");
        return new PackageFragmentAdmin();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "onActivityResult, requestCode: " + String.valueOf(requestCode) +
                ", resultCode: " + String.valueOf(resultCode));
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_ADD_PACKAGE == requestCode && Activity.RESULT_OK == resultCode) {
            showMessage(getString(R.string.package_message_successfully_saved));
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
            FloatingActionButton fab =
                    (FloatingActionButton) getActivity().findViewById(R.id.fab_package);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.showEditPackageActivity();
                }
            });
    }

    @Override
    public void startEditPackageActivity(String userId) {
        Intent intent = new Intent(getContext(), EditPackageActivity.class);
        intent.putExtra(EditPackageFragment.ARGUMENT_EDIT_USER_ID, userId);
        startActivityForResult(intent, REQUEST_ADD_PACKAGE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.i(TAG, "onCreateOptionMenu");
        inflater.inflate(R.menu.menu_packages, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(TAG, "onOptionsItemSelected, itemId: " + String.valueOf(item.getItemId()));
        switch (item.getItemId()) {
            case R.id.action_edit_user:
                /*TODO show Edit User UI*/
                showMessage("Show Edit User");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
