package com.ngadep.fatteningcattle.fragment;

import android.support.v4.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class BaseFragment extends Fragment {

    public String getUid() {
        String result = "";
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            result = mAuth.getCurrentUser().getUid();
        }
        return result;
    }
}
