package com.ngadep.fatteningcattle.progresses.edit;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.ngadep.fatteningcattle.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditProgressFragment extends Fragment implements EditProgressContract.View {
    public static final String ARGUMENT_EDIT_COW_ID = "EDIT_COW_ID";
    public static final String ARGUMENT_EDIT_PROGRESS_ID = "EDIT_PROGRESS_ID";
    private EditProgressContract.Presenter mPresenter;

    EditText mWeight;
    EditText mDate;
    final Calendar mCalendar = Calendar.getInstance();

    public static EditProgressFragment newInstance() {
        return new EditProgressFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.edit_progress_frag, container, false);

        mWeight = (EditText) view.findViewById(R.id.ed_weight);
        mDate = (EditText) view.findViewById(R.id.ed_date);

        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mCalendar.set(year, month, dayOfMonth);
                        mDate.setText(SimpleDateFormat.getDateInstance().format(mCalendar.getTime()));
                    }
                },
                        mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.cleanup();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_edit_progress);
        fab.setImageResource(R.drawable.ic_done);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateForm()) {
                    return;
                }

                mPresenter.saveProgress(
                        Integer.parseInt(mWeight.getText().toString()),
                        mCalendar.getTime().getTime()
                );
            }
        });

    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(mWeight.getText().toString())) {
            mWeight.setError("Required");
            result = false;
        } else {
            mWeight.setError(null);
        }

        if (TextUtils.isEmpty(mDate.getText().toString())) {
            mDate.setError("Required");
            result = false;
        } else {
            mDate.setError(null);
        }

        return result;
    }

    @Override
    public void setPresenter(EditProgressContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setWeight(int weight) {
        mWeight.setText(String.valueOf(weight));
    }

    @Override
    public void setDate(Long date) {
        Date lDate = new Date(date);
        SimpleDateFormat.getDateInstance().format(lDate);
        mCalendar.setTime(lDate);
        mDate.setText(SimpleDateFormat.getDateInstance().format(mCalendar.getTime()));
    }

    @Override
    public void showProgressList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }
}
