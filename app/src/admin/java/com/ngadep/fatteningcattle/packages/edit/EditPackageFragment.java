package com.ngadep.fatteningcattle.packages.edit;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;

import com.ngadep.fatteningcattle.R;

public class EditPackageFragment extends Fragment implements EditPackageContract.View {
    public static final String ARGUMENT_EDIT_PACKAGE_ID = "EDIT_PACKAGE_ID";
    public static final String ARGUMENT_EDIT_USER_ID = "EDIT_USER_ID";
    private static final String TAG = "EditPackageFragment";

    private EditPackageContract.Presenter mPresenter;
    private EditText mName;
    private EditText mLocation;
    private EditText mType;
    private Switch mActive;

    public EditPackageFragment() {
        // Required empty public constructor
    }

    public static EditPackageFragment newInstance() {
        Log.i(TAG, "newInstance");
        return new EditPackageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.edit_package_frag, container, false);

        mName = (EditText) view.findViewById(R.id.ed_package_name);
        mLocation = (EditText) view.findViewById(R.id.ed_package_location);
        mType = (EditText) view.findViewById(R.id.ed_package_type);
        mActive = (Switch) view.findViewById(R.id.sw_package_active);

        return view;
    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume");
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onPause() {
        Log.i(TAG, "onPause");
        super.onPause();
        mPresenter.cleanup();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);

        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_edit_package);
        fab.setImageResource(R.drawable.ic_done);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateForm()) {
                    return;
                }

                mPresenter.savePackage(
                        mName.getText().toString(),
                        mLocation.getText().toString(),
                        Integer.parseInt(mType.getText().toString()),
                        mActive.isChecked()
                );
            }
        });

    }

    @Override
    public void setPresenter(EditPackageContract.Presenter presenter) {
        Log.i(TAG, "setPresenter, to: " + presenter.getClass().getSimpleName());
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        Log.i(TAG, "isActive, value: " + String.valueOf(isAdded()));
        return isAdded();
    }

    @Override
    public void setName(String name) {
        Log.i(TAG, "setName, to: " + name);
        mName.setText(name);
    }

    @Override
    public void setLocation(String location) {
        Log.i(TAG, "setLocation, to: " + location);
        mLocation.setText(location);
    }

    @Override
    public void setType(int type) {
        Log.i(TAG, "setType, to: " + String.valueOf(type));
        mType.setText(String.valueOf(type));
    }

    @Override
    public void setActive(boolean active) {
        Log.i(TAG, "setActive, to: " + String.valueOf(active));
        mActive.setChecked(active);
    }

    @Override
    public void showPackageList() {
        Log.i(TAG, "showPackageList");
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(mName.getText().toString())) {
            mName.setError("Required");
            result = false;
        } else {
            mName.setError(null);
        }

        if (TextUtils.isEmpty(mLocation.getText().toString())) {
            mLocation.setError("Required");
            result = false;
        } else {
            mLocation.setError(null);
        }

        if (TextUtils.isEmpty(mType.getText().toString())) {
            mType.setError("Required");
            result = false;
        } else {
            mType.setError(null);
        }

        Log.i(TAG, "validateForm, result: " + String.valueOf(result));

        return result;
    }
}
