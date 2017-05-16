package com.ngadep.fatteningcattle.repositories;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ngadep.fatteningcattle.datasources.AuthDataSource;

public class LoginRepository implements AuthDataSource {

    private static LoginRepository INSTANCE = null;
    private final FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private LoginRepository() {
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
    }

    public static LoginRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LoginRepository();
        }
        return INSTANCE;
    }

    @Override
    public boolean isLogin() {
        return mUser != null;
    }

    @Override
    public void tryLogIn(String email, String password, final LogInListener callback) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        callback.onLogIn(task.isSuccessful());

                        if (task.isSuccessful()) {
                            mUser = task.getResult().getUser();
                        }
                    }
                });
    }
}
