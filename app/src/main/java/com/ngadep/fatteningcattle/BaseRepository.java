package com.ngadep.fatteningcattle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BaseRepository {

    private final FirebaseDatabase mDatabase;

    protected BaseRepository() {
        mDatabase = FirebaseDatabase.getInstance();
    }

    protected DatabaseReference getRef() {
        return mDatabase.getReference();
    }

    protected String getUid() {
        String result = "";
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            result = mAuth.getCurrentUser().getUid();
        }
        return result;
    }


}
