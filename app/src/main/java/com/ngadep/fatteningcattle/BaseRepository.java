package com.ngadep.fatteningcattle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BaseRepository {

    protected final FirebaseDatabase mDatabase;
    protected final FirebaseAuth mAuth;

    private DatabaseReference priceRef;
    private ValueEventListener priceValueEventListener;

    private DatabaseReference modelRef;
    private ValueEventListener modelValueEventListener;

    protected BaseRepository() {
        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    protected DatabaseReference getRef() {
        return mDatabase.getReference();
    }

    public void getPricePerKg(final PriceListener callback) {
        priceRef = getRef().child("settings").child("price");
        priceValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                callback.onPriceChange((Long) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        priceRef.addValueEventListener(priceValueEventListener);
    }

    protected void getModelFromId(DatabaseReference baseRef, final ModelListener callback) {
        modelRef = baseRef;
        modelValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                callback.onModelChange(dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        modelRef.addValueEventListener(modelValueEventListener);
    }

    public void cleanup() {
        priceRef.removeEventListener(priceValueEventListener);
        modelRef.removeEventListener(modelValueEventListener);
    }

    public String getUid() {
        String result = "";
        if (mAuth.getCurrentUser() != null) {
            result = mAuth.getCurrentUser().getUid();
        }
        return result;
    }

    public interface PriceListener {
        void onPriceChange(Long price);
    }

    public interface ModelListener<T> {
        void onModelChange(T model);
    }
}
