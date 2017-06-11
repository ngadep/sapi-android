package com.ngadep.fatteningcattle.packages;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.models.Package;

import java.util.HashMap;
import java.util.Map;

public class PackageRepository extends BaseRepository {

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

    Query getPackagesFromUserId(String uid) {
        return mRef.child(uid);
    }

    public void savePackage(@Nullable String packageId, @NonNull Package pkg) {
        if (packageId == null) {
            packageId = mRef.push().getKey();
        }

        Map<String, Object> packageValues = pkg.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/packages/" + packageId, packageValues);
        childUpdates.put("/user-packages/" + pkg.getUid() + "/" + packageId, packageValues);

        getRef().updateChildren(childUpdates);

    }

    public void savePackage(@NonNull Package pkg) {
        this.savePackage(null, pkg);
    }

}
