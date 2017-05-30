package com.ngadep.fatteningcattle.news;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ngadep.fatteningcattle.R;

public class NewsFragment extends Fragment {

    private static final String TAG = "NewsFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        Log.i(TAG, "inflate fragment news");
        return inflater.inflate(R.layout.fragment_news, container, false);
    }
}
