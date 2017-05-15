package com.ngadep.fatteningcattle.repositories;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ngadep.fatteningcattle.contracts.LoginContract;

public class LoginRepository implements LoginContract.Repository {

    private static LoginRepository INSTANCE = null;
    private final FirebaseAuth mAuth;
    private boolean login;

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
        return this.login = mAuth.getCurrentUser() != null;
    }

    @Override
    public boolean tryLogIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        LoginRepository.this.login = task.isSuccessful();
                    }
                });
        return this.login;
    }
}
