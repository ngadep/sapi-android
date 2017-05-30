package com.ngadep.fatteningcattle.users;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.data.models.User;

public class UserRepository extends BaseRepository {

    private static final String USERS_REF = "users";
    private static final String TAG = "UserRepository";
    private static UserRepository INSTANCE = null;
    private final DatabaseReference mRef;

    private UserRepository() {
        mRef = getRef().child(USERS_REF);
    }

    public static UserRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository();
        }
        return INSTANCE;
    }

    public void saveUser(final User user, String password) {
        mAuth.createUserWithEmailAndPassword(user.getEmail(), password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            saveToDatabase(task.getResult().getUser().getUid(), user);
                        } else {
                            Log.e(TAG, "error on create user");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, e.getMessage());
                    }
                });
    }

    private void saveToDatabase(String uid, User user) {
        mRef.child(uid).setValue(user);
    }

}
