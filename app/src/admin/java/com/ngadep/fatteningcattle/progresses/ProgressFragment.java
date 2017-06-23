package com.ngadep.fatteningcattle.progresses;

import android.app.Activity;
import android.content.Intent;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.progresses.edit.EditProgressActivity;
import com.ngadep.fatteningcattle.progresses.edit.EditProgressFragment;

public class ProgressFragment extends BaseProgressFragment {
    private static final int REQUEST_EDIT_PACKAGE = 1;

    public static ProgressFragment newInstance() {
        return new ProgressFragment();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_EDIT_PACKAGE == requestCode && Activity.RESULT_OK == resultCode) {
            showMessage(getString(R.string.progress_message_successfully_saved));
        }
    }

    @Override
    public void showEditProgressActivity(String cowId, String progressId) {
        Intent intent = new Intent(getActivity(), EditProgressActivity.class);
        intent.putExtra(EditProgressFragment.ARGUMENT_EDIT_COW_ID, cowId);
        intent.putExtra(EditProgressFragment.ARGUMENT_EDIT_PROGRESS_ID, progressId);
        startActivityForResult(intent, REQUEST_EDIT_PACKAGE);
    }
}
