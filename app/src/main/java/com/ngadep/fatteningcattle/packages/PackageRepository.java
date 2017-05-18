package com.ngadep.fatteningcattle.packages;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.BaseRepository;

public class PackageRepository extends BaseRepository implements PackageContract.Repository {

    private static PackageRepository INSTANCE = null;
    private final DatabaseReference mRef;

    private PackageRepository() {
        super();
        mRef = getRef().child("user-packages");
    }

    public static PackageRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PackageRepository();
        }

        return INSTANCE;
    }

    @Override
    public Query getPackagesFromCurrentUser() {
        return mRef.child(getUid());
    }
}
