package com.ngadep.fatteningcattle.packages.edit;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;

import com.ngadep.fatteningcattle.R;

public class EditPackageFragment extends Fragment implements EditPackageContract.View {
    public static final String ARGUMENT_EDIT_PACKAGE_ID = "EDIT_PACKAGE_ID";
    public static final String ARGUMENT_EDIT_USER_ID = "EDIT_USER_ID";

    private EditPackageContract.Presenter mPresenter;
    private EditText mName;
    private EditText mLocation;
    private EditText mType;
    private Switch mActive;

    public EditPackageFragment() {
        // Required empty public constructor
    }

    public static EditPackageFragment newInstance() {
        return new EditPackageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
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
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setName(String name) {
        mName.setText(name);
    }

    @Override
    public void setLocation(String location) {
        mLocation.setText(location);
    }

    @Override
    public void setType(int type) {
        mType.setText(String.valueOf(type));
    }

    @Override
    public void setActive(boolean active) {
        mActive.setChecked(active);
    }

    @Override
    public void showPackageList() {
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

        return result;
    }
}
