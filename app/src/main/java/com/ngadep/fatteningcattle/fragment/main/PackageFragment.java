package com.ngadep.fatteningcattle.fragment.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.fragment.BaseFragment;
import com.ngadep.fatteningcattle.models.Package;
import com.ngadep.fatteningcattle.viewholder.PackageViewHolder;

public class PackageFragment extends BaseFragment {

    private static final String TAG = "PackageFragment";
    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    private FirebaseRecyclerAdapter<Package, PackageViewHolder> mAdapter;
    private RecyclerView mRecycler;

    public PackageFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_package, container, false);

        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

        mRecycler = (RecyclerView) rootView.findViewById(R.id.package_list);
        mRecycler.setHasFixedSize(true);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Set up Layout Manager, reverse layout
        LinearLayoutManager mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query Packages = getQuery(mDatabase);
        mAdapter = new FirebaseRecyclerAdapter<Package, PackageViewHolder>(Package.class, 
                R.layout.item_package, PackageViewHolder.class, Packages) {
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

    private Query getQuery(DatabaseReference databaseReference) {
        Log.i(TAG, "querying user-packages");
        return databaseReference.child("user-packages")
                .child(getUid());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cleanup();
        }
    }
}
