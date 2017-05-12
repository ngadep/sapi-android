package com.ngadep.fatteningcattle.repositories;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

class BaseRepository {

    private FirebaseDatabase mDatabase;

    BaseRepository() {
        mDatabase = FirebaseDatabase.getInstance();
    }

    DatabaseReference getRef() {
        return mDatabase.getReference();
    }

    String getUid() {
        String result = "";
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            result = mAuth.getCurrentUser().getUid();
        }
        return result;
    }


}
