package com.ngadep.fatteningcattle.repositories;

import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.ngadep.fatteningcattle.contracts.LoginContract;

public class LoginRepository implements LoginContract.Repository {

    private static LoginRepository INSTANCE = null;
    private FirebaseAuth mAuth;

    private LoginRepository() {
        mAuth = FirebaseAuth.getInstance();
    }

    public static LoginRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LoginRepository();
        }
        return INSTANCE;
    }

    @Override
    public boolean isLogin() {
        return (mAuth.getCurrentUser() != null);
    }
}
