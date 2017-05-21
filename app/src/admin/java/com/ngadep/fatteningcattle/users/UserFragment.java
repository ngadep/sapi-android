package com.ngadep.fatteningcattle.users;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.data.models.User;

public class UserFragment extends Fragment implements UserView {

    private static final String TAG = "UserFragment";

    private UserPresenter mPresenter;

    private FirebaseRecyclerAdapter<User, UserViewHolder> mAdapter;
    private RecyclerView mRecycler;

    public UserFragment() {
        mPresenter = new UserPresenter(this);
    }

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_user, container, false);

        mRecycler = (RecyclerView) rootView.findViewById(R.id.user_list);
        mRecycler.setHasFixedSize(true);

        return rootView;
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
    }

    @Override
    public void showAllUser(Query users) {
        Log.i(TAG, "querying user-packages");
        // Set up Layout Manager, reverse layout
        LinearLayoutManager mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        mAdapter = new FirebaseRecyclerAdapter<User, UserViewHolder>(User.class,
                R.layout.item_user, UserViewHolder.class, users) {
            @Override
            protected void populateViewHolder(final UserViewHolder viewHolder, final User model, final int position) {
                // Set click listener for the whole package view
                final String userKey = getRef(position).getKey();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), userKey, Toast.LENGTH_SHORT).show();
                    }
                });

                viewHolder.bindToPackage(model);
            }
        };
        mRecycler.setAdapter(mAdapter);
    }
}
