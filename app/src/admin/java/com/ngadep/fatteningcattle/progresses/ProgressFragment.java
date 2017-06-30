package com.ngadep.fatteningcattle.progresses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.cows.edit.EditCowActivity;
import com.ngadep.fatteningcattle.cows.edit.EditCowFragment;
import com.ngadep.fatteningcattle.progresses.edit.EditProgressActivity;
import com.ngadep.fatteningcattle.progresses.edit.EditProgressFragment;

public class ProgressFragment extends BaseProgressFragment {
    private static final int REQUEST_EDIT_PACKAGE = 1;
    private static final int REQUEST_EDIT_COW = 2;

    public static ProgressFragment newInstance() {
        return new ProgressFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_progress, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit_cow:
                mPresenter.showEditCowActivity();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_EDIT_PACKAGE == requestCode && Activity.RESULT_OK == resultCode) {
            showMessage(getString(R.string.progress_message_successfully_saved));
        }
    }

    @Override
    public void startEditProgressActivity(String cowId, String progressId) {
        Intent intent = new Intent(getActivity(), EditProgressActivity.class);
        intent.putExtra(EditProgressFragment.ARGUMENT_EDIT_COW_ID, cowId);
        intent.putExtra(EditProgressFragment.ARGUMENT_EDIT_PROGRESS_ID, progressId);
        startActivityForResult(intent, REQUEST_EDIT_PACKAGE);
    }

    @Override
    public void startEditCowActivity(String cowId, String packageId) {
        Intent intent = new Intent(getActivity(), EditCowActivity.class);
        intent.putExtra(EditCowFragment.ARGUMENT_EDIT_COW_ID, cowId);
        intent.putExtra(EditCowFragment.ARGUMENT_EDIT_PACKAGE_ID, packageId);
        startActivityForResult(intent, REQUEST_EDIT_COW);
    }
}
