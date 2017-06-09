package com.ngadep.fatteningcattle.users;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.BuildConfig;
import com.ngadep.fatteningcattle.models.User;

public class UserRepository extends BaseRepository {

    private static final String USERS_REF = "users";
    private static final String TAG = "UserRepository";
    private static UserRepository INSTANCE = null;
    private final DatabaseReference mRef;
    private FirebaseUser mUser;

    private UserRepository() {
        mRef = getRef().child(USERS_REF);
    }

    public static UserRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository();
        }
        return INSTANCE;
    }

    Query getUsersQuery() {
        return mRef.orderByChild("admin").equalTo(false);
    }

    public void registerUser(final User user, String password, final RegisterListener callback) {
        mAuth.createUserWithEmailAndPassword(user.getEmail(), password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            saveUserToDatabase(task.getResult().getUser().getUid(), user);
                        }

                        callback.onRegister(task.isSuccessful());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, e.getMessage());
                    }
                });
    }

    private void saveUserToDatabase(String uid, User user) {
        mRef.child(uid).setValue(user);
    }

    public void userIsLogin(final LogInListener callback) {
        mUser = mAuth.getCurrentUser();
        if (mUser != null) {
            if (BuildConfig.FLAVOR.equals("admin")) {
                userIsAdminCallback(mUser.getUid(), new LoginIsAdminListener() {
                    @Override
                    public void onLoginIsAdmin(boolean isAdmin) {
                        callback.onLogIn(isAdmin);
                    }
                });
            } else {
                callback.onLogIn(mUser != null);
            }
        } else {
            callback.onLogIn(false);
        }
    }

    public void tryLogIn(String email, String password, final LogInListener callback) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mUser = task.getResult().getUser();
                            if (BuildConfig.FLAVOR.equals("admin")) {
                                userIsAdminCallback(mUser.getUid(), new LoginIsAdminListener() {
                                    @Override
                                    public void onLoginIsAdmin(boolean isAdmin) {
                                        callback.onLogIn(isAdmin);
                                    }
                                });
                            } else {
                                callback.onLogIn(mUser != null);
                            }
                        } else {
                            callback.onLogIn(false);
                        }
                    }
                });
    }

    private void userIsAdminCallback(String uid, final LoginIsAdminListener callback) {
        DatabaseReference mUserInfo = getRef().child("users").child("uid");
        mUserInfo.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                callback.onLoginIsAdmin(dataSnapshot.getValue(User.class).isAdmin());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onLoginIsAdmin(false);
            }
        });
    }

    public interface LogInListener {
        void onLogIn(boolean success);
    }

    interface LoginIsAdminListener {
        void onLoginIsAdmin(boolean isAdmin);
    }

    public interface RegisterListener {
        void onRegister(boolean success);
    }
}
