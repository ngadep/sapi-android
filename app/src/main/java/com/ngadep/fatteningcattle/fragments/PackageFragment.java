package com.ngadep.fatteningcattle.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.contracts.PackageContract;
import com.ngadep.fatteningcattle.models.Package;
import com.ngadep.fatteningcattle.presenter.PackagePresenter;
import com.ngadep.fatteningcattle.repositories.PackageRepository;
import com.ngadep.fatteningcattle.viewholders.PackageViewHolder;

public class PackageFragment extends BaseFragment implements PackageContract.View {

    private static final String TAG = "PackageFragment";

    private PackagePresenter mPresenter;

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
                // final DatabaseReference packagesRef = getRef(position);

                // Set click listener for the whole package view
                // final String packageKey = packagesRef.getKey();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: Package Details Activity
                        // Launch Package Details Activity
                        // Intent intent = new Intent(getActivity(), PackageDetailActivity.class);
                        // intent.putExtra(PackageDetailActivity.EXTRA_POST_KEY, packageKey);
                        // startActivity(intent);
                    }
                });

                viewHolder.bindToPackage(model);
            }
        };
        mRecycler.setAdapter(mAdapter);
    }
}
