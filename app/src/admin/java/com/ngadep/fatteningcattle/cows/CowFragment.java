package com.ngadep.fatteningcattle.cows;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.cows.edit.EditCowActivity;
import com.ngadep.fatteningcattle.cows.edit.EditCowFragment;
import com.ngadep.fatteningcattle.models.Package;
import com.ngadep.fatteningcattle.packages.edit.EditPackageActivity;
import com.ngadep.fatteningcattle.packages.edit.EditPackageFragment;

public class CowFragment extends BaseCowFragment {

    private static final int REQUEST_ADD_COW = 1;
    private static final int REQUEST_EDIT_PACKAGE = 2;

    public CowFragment() {
    }

    public static CowFragment newInstance() {
        return new CowFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void startEditCow(String packageId) {
        Intent intent = new Intent(getActivity(), EditCowActivity.class);
        intent.putExtra(EditCowFragment.ARGUMENT_EDIT_PACKAGE_ID, packageId);
        startActivityForResult(intent, REQUEST_ADD_COW);
    }

    @Override
    public void showEditPackage(String packageId, Package packageModel) {
        Intent intent = new Intent(getActivity(), EditPackageActivity.class);
        intent.putExtra(EditPackageFragment.ARGUMENT_EDIT_USER_ID, packageModel.getUid());
        intent.putExtra(EditPackageFragment.ARGUMENT_EDIT_PACKAGE_ID, packageId);
        intent.putExtra(EditPackageFragment.ARGUMENT_EDIT_PACKAGE_MODEL, packageModel);
        startActivityForResult(intent, REQUEST_EDIT_PACKAGE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_cows, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit_package:
                mPresenter.showEditPackageUi();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
