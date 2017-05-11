package com.ngadep.fatteningcattle.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.fragments.BaseFragment;

public class NewsFragment extends BaseFragment {

    private static final String TAG = "NewsFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        Log.i(TAG, "inflate fragment news");
        return inflater.inflate(R.layout.fragment_news, container, false);
    }
}
