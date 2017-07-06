package com.ngadep.fatteningcattle.main;

import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.news.NewsFragment;
import com.ngadep.fatteningcattle.users.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseMainActivity {

    private static final String TAG = "MainActivity";

    @Override
    public void setFabVisibility(FloatingActionButton fab) {
        Log.i(TAG, "setFabVisibility");
        fab.setVisibility(View.VISIBLE);
    }

    @Override
    public List<MainFragment> getFragments() {
        Log.i(TAG, "getFragments");
        List<MainFragment> result = new ArrayList<>();
        result.add(new MainFragment(R.string.heading_main_user, UserFragment.newInstance()));
        result.add(new MainFragment(R.string.heading_main_news, new NewsFragment()));

        return result;
    }
}