package com.ngadep.fatteningcattle.packages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.cattle.CowActivity;
import com.ngadep.fatteningcattle.data.models.Package;

public class PackageFragment extends Fragment implements PackageContract.View {

    private static final String TAG = "PackageFragment";

    private final PackagePresenter mPresenter;

    private FirebaseRecyclerAdapter<Package, PackageViewHolder> mAdapter;
    private RecyclerView mRecycler;

    public PackageFragment() {
        PackageContract.Repository mRepository = PackageRepository.getInstance();
        mPresenter = new PackagePresenter(this, mRepository);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_package, container, false);

        mRecycler = (RecyclerView) rootView.findViewById(R.id.package_list);
        mRecycler.setHasFixedSize(true);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getUserPackages();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cleanup();
        }
    }

    @Override
    public void getPackages(Query packages) {
        Log.i(TAG, "querying user-packages");
        // Set up Layout Manager, reverse layout
        LinearLayoutManager mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        mAdapter = new FirebaseRecyclerAdapter<Package, PackageViewHolder>(Package.class,
                R.layout.item_package, PackageViewHolder.class, packages) {
            @Override
            protected void populateViewHolder(final PackageViewHolder viewHolder, final Package model, final int position) {
                // Set click listener for the whole package view
                final String packageKey = getRef(position).getKey();
                final String packageName = getItem(position).getName();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.startCowActivity(packageKey, packageName);
                    }
                });

                viewHolder.bindToPackage(model);
            }
        };
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void startCowActivity(String packageKey, String packageName) {
        // Launch Cow Activity
        Intent intent = new Intent(getActivity(), CowActivity.class);
        intent.putExtra(CowActivity.EXTRA_PACKAGE_ID, packageKey);
        intent.putExtra(CowActivity.EXTRA_PACKAGE_NAME, packageName);
        startActivity(intent);
    }
}
