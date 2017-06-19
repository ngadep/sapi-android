package com.ngadep.fatteningcattle.cows.edit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.ngadep.fatteningcattle.R;

public class EditCowFragment extends Fragment implements EditCowContract.View {
    public static final String ARGUMENT_EDIT_COW_ID = "EDIT_COW_ID";
    public static final String ARGUMENT_EDIT_COW_MODEL = "EDIT_COW_MODEL";

    private EditCowContract.Presenter mPresenter;

    EditText mEarTag;
    Spinner mSex;
    EditText mWeight;
    EditText mDate;

    public static EditCowFragment newInstance() {
        return new EditCowFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.edit_cow_frag, container, false);

        mEarTag = (EditText) view.findViewById(R.id.ed_ear_tag);
        mSex = (Spinner) view.findViewById(R.id.sp_sex);
        ArrayAdapter<CharSequence> sexAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.sex_array, android.R.layout.simple_spinner_item);
        sexAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSex.setAdapter(sexAdapter);
        mWeight = (EditText) view.findViewById(R.id.ed_weight);
        mDate = (EditText) view.findViewById(R.id.date);

        return view;
    }

    @Override
    public void setPresenter(EditCowContract.Presenter presenter) {
        mPresenter = presenter;
    }

}
