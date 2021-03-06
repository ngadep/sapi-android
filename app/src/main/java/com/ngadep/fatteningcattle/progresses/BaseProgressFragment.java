package com.ngadep.fatteningcattle.progresses;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.BuildConfig;
import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.models.Cow;
import com.ngadep.fatteningcattle.models.Progress;

import java.util.ArrayList;

public abstract class BaseProgressFragment extends Fragment implements ProgressContract.View {

    private static final String TAG = "BaseProgressFragment";
    protected ProgressContract.Presenter mPresenter;
    private FirebaseRecyclerAdapter<Progress, ProgressViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private BarChart mChart;
    ArrayList<BarEntry> chartValue;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.progress_frag, container, false);

        mRecycler = (RecyclerView) rootView.findViewById(R.id.progress_list);
        mRecycler.setHasFixedSize(true);
        mRecycler.setAdapter(mAdapter);

        chartValue = new ArrayList<>();

        mChart = (BarChart) rootView.findViewById(R.id.chart_progress);
        mChart.getDescription().setEnabled(false);

        return rootView;
    }

    private void setChartData(int position, float value) {
        Log.i(TAG, "setChartData, position: " + String.valueOf(position) + " , value : "
                + String.valueOf(value));
        int pos = position + 1;
        if (pos > chartValue.size()) {
            for (int i = chartValue.size(); i < pos; i++) {
                chartValue.add(new BarEntry(i, value));
            }
        }

        chartValue.set(position, new BarEntry(pos, value));

        BarDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(chartValue);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
            mChart.invalidate();
        } else {
            set1 = new BarDataSet(chartValue, "Progress");

            set1.setDrawIcons(false);

            set1.setColors(ColorTemplate.MATERIAL_COLORS);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);

            mChart.setData(data);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_progress);
        if (BuildConfig.FLAVOR.equals("user")) {
            fab.hide();
        } else {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.showEditProgressActivity();
                }
            });
        }
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
        if (mAdapter != null) {
            mAdapter.cleanup();
        }
        mPresenter.cleanup();
    }

    @Override
    public void setPresenter(ProgressContract.Presenter presenter) {
        Log.i(TAG, "setPresenter to " + String.valueOf(presenter.getClass().getSimpleName()));
        mPresenter = presenter;
    }

    protected void showMessage(String message) {
        Log.i(TAG, "showMessage: " + message);
        Snackbar.make(mRecycler, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void getAllCowProgress(Query query) {
        Log.i(TAG, "getAllCowProgress");
        // Set up Layout Manager, reverse layout
        LinearLayoutManager mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(false);
        mManager.setStackFromEnd(false);
        mRecycler.setLayoutManager(mManager);

        // Set up Recycler Adapter with the Query
        mAdapter = new FirebaseRecyclerAdapter<Progress, ProgressViewHolder>(Progress.class,
                R.layout.progress_item, ProgressViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final ProgressViewHolder viewHolder, final Progress model, final int position) {
                setChartData(position, model.getWeight());
                model.setPrice(mPresenter.getPricePerKg());
                final String progressId = getRef(position).getKey();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.showEditProgressActivity(progressId);
                    }
                });
                viewHolder.bindToProgress(model);
            }
        };
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void notifyPriceChange() {
        Log.i(TAG, "notifyPriceChange");
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyCowChange(Cow model) {
        Log.i(TAG, "notifyCowChange");
        getActivity().setTitle(model.getEar_tag());
    }
}
