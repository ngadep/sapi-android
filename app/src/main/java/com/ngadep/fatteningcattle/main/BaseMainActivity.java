package com.ngadep.fatteningcattle.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.login.LogInActivity;

import java.util.List;

public abstract class BaseMainActivity extends AppCompatActivity {

    protected FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        SectionsPagerAdapter mSectionsPagerAdapter = getSectionPagerAdapter();

        // Set up the ViewPager with the sections adapter.
        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        fab = (FloatingActionButton) findViewById(R.id.fab_main);
        setFabVisibility(fab);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, LogInActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class MainFragment {
        int resourceText;
        Fragment fragment;

        MainFragment(int resourceText, Fragment fragment) {
            this.resourceText = resourceText;
            this.fragment = fragment;
        }
    }

    public abstract void setFabVisibility(FloatingActionButton fab);

    public abstract List<MainFragment> getFragments();

    private SectionsPagerAdapter getSectionPagerAdapter() {
        return new SectionsPagerAdapter(getSupportFragmentManager(), getFragments());
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        List<MainFragment> mFragments;

        SectionsPagerAdapter(FragmentManager fm, List<MainFragment> fragment) {
            super(fm);
            mFragments = fragment;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            return mFragments.get(position).fragment;
        }

        @Override
        public int getCount() {
            // Show total fragments.
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getString(mFragments.get(position).resourceText);
        }
    }
}
