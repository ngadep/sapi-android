package com.ngadep.fatteningcattle.cows;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.models.Cow;

public class CowActivity extends AppCompatActivity implements CowContract.View {

    public static final String EXTRA_PACKAGE_ID = "PACKAGE_ID";
    public static final String EXTRA_PACKAGE_NAME = "PACKAGE_NAME";

    private CowPresenter mPresenter;

    private FirebaseRecyclerAdapter<Cow, CowViewHolder> mAdapter;
    private RecyclerView mRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cow_act);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        // Get the requested package id
        String packageId = getIntent().getStringExtra(EXTRA_PACKAGE_ID);
        String packageName = getIntent().getStringExtra(EXTRA_PACKAGE_NAME);

        setTitle(packageName);

        CowContract.Repository mRepository = CowRepository.getInstance();
        mPresenter = new CowPresenter(this, mRepository, packageId);
        mRecycler = (RecyclerView) findViewById(R.id.cow_list);
        mRecycler.setHasFixedSize(true);
        mPresenter.getPackageCows();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cleanup();
        }
    }

    @Override
    public void getAllPackageCow(Query cows) {
        // Set up Layout Manager, reverse layout
        LinearLayoutManager mManager = new LinearLayoutManager(this);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        mAdapter = new FirebaseRecyclerAdapter<Cow, CowViewHolder>(Cow.class,
                R.layout.cow_item, CowViewHolder.class, cows) {
            @Override
            protected void populateViewHolder(final CowViewHolder viewHolder, final Cow model, final int position) {
                final String cowId = getRef(position).getKey();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.startCowDetailActivity(cowId);
                    }
                });
                viewHolder.bindToCow(model);
            }
        };
        mRecycler.setAdapter(mAdapter);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}