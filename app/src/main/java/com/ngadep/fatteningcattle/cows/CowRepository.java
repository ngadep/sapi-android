package com.ngadep.fatteningcattle.cows;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.models.Cow;

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

    Query getPackageCowFromId(String packageId) {
        return mRef.child(packageId);
    }

    void getPricePerKg(final PriceListener callback) {
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

    void cleanup() {
        priceRef.removeEventListener(priceValueEventListener);
    }

    public void saveCow(Cow cow) {
        this.saveCow(null, cow);
    }

    public void saveCow(String mCowId, Cow cow) {
        if (mCowId == null) {
            mCowId = mRef.push().getKey();
        }

        mRef.child(cow.getPackage_id()).child(mCowId).setValue(cow);
    }

    interface PriceListener {
        void onPriceChange(Long price);
    }
}
