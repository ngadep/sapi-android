package com.ngadep.fatteningcattle.users;

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
import com.ngadep.fatteningcattle.models.User;
import com.ngadep.fatteningcattle.packages.PackageActivity;

public class UserFragment extends Fragment implements UserContract.View {

    private static final String TAG = "UserFragment";

    private UserContract.Presenter mPresenter;

    private FirebaseRecyclerAdapter<User, UserViewHolder> mAdapter;
    private RecyclerView mRecycler;

    public UserFragment() {
        mPresenter = new UserPresenter(this, UserRepository.getInstance());
    }

    public static UserFragment newInstance() {
        Log.i(TAG, "newInstance");
        return new UserFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.user_frag, container, false);

        mRecycler = (RecyclerView) rootView.findViewById(R.id.user_list);
        mRecycler.setHasFixedSize(true);

        return rootView;
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
    }

    @Override
    public void showAllUser(Query users) {
        Log.i(TAG, "querying user-packages");
        // Set up Layout Manager, reverse layout
        LinearLayoutManager mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        // Set up RecyclerAdapter with the Query
        mAdapter = new FirebaseRecyclerAdapter<User, UserViewHolder>(User.class,
                R.layout.user_item, UserViewHolder.class, users) {
            @Override
            protected void populateViewHolder(final UserViewHolder viewHolder, final User model, final int position) {
                // Set click listener for the whole package view
                final String userKey = getRef(position).getKey();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getView() != null) {
                            mPresenter.showPackageActivity(userKey);
                        }
                    }
                });

                viewHolder.bindToPackage(model);
            }
        };
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void startPackageActivity(String userId) {
        Log.i(TAG, "startPackageActivity");
        Intent intent = new Intent(getActivity(), PackageActivity.class);
        intent.putExtra(PackageActivity.EXTRA_USER_ID, userId);
        startActivity(intent);
    }

    @Override
    public void setPresenter(UserContract.Presenter presenter) {
        Log.i(TAG, "setPresenter to: " + presenter.getClass().getSimpleName());
        mPresenter = presenter;
    }
}
