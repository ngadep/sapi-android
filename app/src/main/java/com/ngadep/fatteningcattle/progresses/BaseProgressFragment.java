package com.ngadep.fatteningcattle.progresses;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.BuildConfig;
import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.models.Cow;
import com.ngadep.fatteningcattle.models.Progress;

public class BaseProgressFragment extends Fragment implements ProgressContract.View {

    protected ProgressContract.Presenter mPresenter;
    private FirebaseRecyclerAdapter<Progress, ProgressViewHolder> mAdapter;
    private RecyclerView mRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.progress_frag, container, false);

        mRecycler = (RecyclerView) rootView.findViewById(R.id.progress_list);
        mRecycler.setHasFixedSize(true);
        mRecycler.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_progress);
        if (BuildConfig.FLAVOR.equals("user")) {
            fab.hide();
        } else {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: show add progress ui
                    //mPresenter.startAddProgressUi();
                }
            });
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cleanup();
        }
        mPresenter.cleanup();
    }

    @Override
    public void setPresenter(ProgressContract.Presenter presenter) {
        mPresenter = presenter;
    }

    protected void showMessage(String message) {
        Snackbar.make(mRecycler, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void getAllCowProgress(Query query) {
        // Set up Layout Manager, reverse layout
        LinearLayoutManager mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        mAdapter = new FirebaseRecyclerAdapter<Progress, ProgressViewHolder>(Progress.class,
                R.layout.progress_item, ProgressViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final ProgressViewHolder viewHolder, final Progress model, final int position) {
                model.setPrice(mPresenter.getPricePerKg());
                final String progressId = getRef(position).getKey();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.startProgressDetailActivity(progressId);
                    }
                });
                viewHolder.bindToProgress(model);
            }
        };
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void notifyPriceChange() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyCowChange(Cow model) {
        getActivity().setTitle(model.getEar_tag());
    }
}
