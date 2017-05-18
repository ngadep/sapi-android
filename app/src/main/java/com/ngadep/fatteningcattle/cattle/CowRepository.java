package com.ngadep.fatteningcattle.cattle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.BaseRepository;

public class CowRepository extends BaseRepository implements CowContract.Repository {
    private static CowRepository INSTANCE = null;
    private final DatabaseReference mRef;

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

    @Override
    public Query getPackageCowFromId(String packageId) {
        return mRef.child(packageId);
    }
}