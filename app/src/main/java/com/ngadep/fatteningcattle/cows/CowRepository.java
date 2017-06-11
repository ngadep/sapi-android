package com.ngadep.fatteningcattle.cows;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ngadep.fatteningcattle.BaseRepository;

public class CowRepository extends BaseRepository {
    private static CowRepository INSTANCE = null;
    private final DatabaseReference mRef;
    private DatabaseReference priceRef;
    private ValueEventListener priceValueEventListener;

    private CowRepository() {
        super();
        mRef = getRef().child("package-cows");
    }

    public static CowRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CowRepository();
        }

        return INSTANCE;
    }

    public Query getPackageCowFromId(String packageId) {
        return mRef.child(packageId);
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

    public void cleanup() {
        priceRef.removeEventListener(priceValueEventListener);
    }

    interface PriceListener {
        void onPriceChange(Long price);
    }
}
