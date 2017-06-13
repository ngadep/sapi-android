package com.ngadep.fatteningcattle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BaseRepository {

    protected final FirebaseDatabase mDatabase;
    protected final FirebaseAuth mAuth;

    protected BaseRepository() {
        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    protected DatabaseReference getRef() {
        return mDatabase.getReference();
    }

    public String getUid() {
        String result = "";
        if (mAuth.getCurrentUser() != null) {
            result = mAuth.getCurrentUser().getUid();
        }
        return result;
    }
}
