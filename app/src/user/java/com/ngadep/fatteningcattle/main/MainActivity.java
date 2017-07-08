package com.ngadep.fatteningcattle.main;

import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.news.NewsFragment;
import com.ngadep.fatteningcattle.packages.PackageFragment;
import com.ngadep.fatteningcattle.packages.PackagePresenter;
import com.ngadep.fatteningcattle.packages.PackageRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseMainActivity {

    private static final String TAG = "MainActivity";

    @Override
    public void setFabVisibility(FloatingActionButton fab) {
        Log.i(TAG, "setFabVisibility GONE");
        fab.setVisibility(View.GONE);
    }

    @Override
    public List<MainFragment> getFragments() {
        Log.i(TAG, "getFragments");
        List<MainFragment> result = new ArrayList<>();
        result.add(new MainFragment(R.string.heading_main_package, getPackageFragment()));
        result.add(new MainFragment(R.string.heading_main_news, new NewsFragment()));

        return result;
    }

    public PackageFragment getPackageFragment() {
        Log.i(TAG, "getPackageFragment");
        PackageFragment packageFragment = PackageFragment.newInstance();
        new PackagePresenter(null, packageFragment, PackageRepository.getInstance());

        return packageFragment;
    }
}
