package com.ngadep.fatteningcattle.cows;

import android.content.Intent;
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
import com.ngadep.fatteningcattle.progresses.ProgressActivity;

public abstract class BaseCowFragment extends Fragment implements CowContract.View {

    CowContract.Presenter mPresenter;
    private FirebaseRecyclerAdapter<Cow, CowViewHolder> mAdapter;
    private RecyclerView mRecycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.cow_frag, container, false);

        mRecycler = (RecyclerView) rootView.findViewById(R.id.cow_list);
        mRecycler.setHasFixedSize(true);
        mRecycler.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_cow);
        if (BuildConfig.FLAVOR.equals("user")) {
            fab.hide();
        } else {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.startAddCowUi();
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
    public void setPresenter(CowContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getAllPackageCow(Query cows) {
        // Set up Layout Manager, reverse layout
        LinearLayoutManager mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        mAdapter = new FirebaseRecyclerAdapter<Cow, CowViewHolder>(Cow.class,
                R.layout.cow_item, CowViewHolder.class, cows) {
            @Override
            protected void populateViewHolder(final CowViewHolder viewHolder, final Cow model, final int position) {
                model.setPrice(mPresenter.getPricePerKg());
                final String cowId = getRef(position).getKey();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.startCowProgress(cowId);
                    }
                });
                viewHolder.bindToCow(model);
            }
        };
        mRecycler.setAdapter(mAdapter);

    }

    protected void showMessage(String message) {
        Snackbar.make(mRecycler, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void notifyPriceChange() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void startCowProgressActivity(String cowId) {
        Intent intent = new Intent(getActivity(), ProgressActivity.class);
        intent.putExtra(ProgressActivity.EXTRA_COW_ID, cowId);
        startActivity(intent);
    }
}
