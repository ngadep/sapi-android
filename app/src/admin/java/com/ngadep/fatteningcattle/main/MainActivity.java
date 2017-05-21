package com.ngadep.fatteningcattle.main;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.news.NewsFragment;
import com.ngadep.fatteningcattle.users.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseMainActivity {

    @Override
    public List<MainFragment> getFragments() {
        List<MainFragment> result = new ArrayList<>();
        result.add(new MainFragment(R.string.heading_main_user, UserFragment.newInstance()));
        result.add(new MainFragment(R.string.heading_main_news, new NewsFragment()));

        return result;
    }
}