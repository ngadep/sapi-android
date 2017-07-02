package com.ngadep.fatteningcattle.cows;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.storage.StorageReference;
import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.models.Cow;
import com.ngadep.fatteningcattle.models.Package;
import com.ngadep.fatteningcattle.models.Progress;

import java.util.HashMap;
import java.util.Map;

public class CowRepository extends BaseRepository {
    private static final String COW_TREE = "cows";
    private static final String PACKAGE_TREE = "packages";
    private static final String PACKAGE_COWS_TREE = "package-cows";
    private static final String HEAD_JPG = "head.jpg";

    private static CowRepository INSTANCE = null;
    private final DatabaseReference mRef;
    private final DatabaseReference mCowRef;
    private final DatabaseReference mPackageRef;

    private CowRepository() {
        super();
        mRef = getRef().child(PACKAGE_COWS_TREE);
        mCowRef = getRef().child(COW_TREE);
        mPackageRef = getRef().child(PACKAGE_TREE);
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

    public void saveCow(Cow cow) {
        this.saveCow(null, cow);
    }

    public void saveCow(String cowId, Cow cow) {
        DatabaseReference localRef = getRef().child("cows");

        if (cowId == null) {
            cowId = localRef.push().getKey();
            Progress lProgress = new Progress(cowId, cow.getDate(), cow.getWeight());
            this.saveCowProgress(lProgress);
        }

        Map<String, Object> cowValues = cow.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/cows/" + cowId, cowValues);
        childUpdates.put("/package-cows/" + cow.getPackage_id() + "/" + cowId, cowValues);

        getRef().updateChildren(childUpdates);
    }
    public void getCowFromId(String cowId, ModelListener<Cow> callback) {
        getModelFromId(mCowRef.child(cowId), Cow.class, callback);
    }

    private void saveCowProgress(Progress progress) {
        DatabaseReference localRef = getRef().child("progresses");

        String progressId = localRef.push().getKey();

        Map<String, Object> cowValues = progress.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/progresses/" + progressId, cowValues);
        childUpdates.put("/cow-progresses/" + progress.getCow_id() + "/" + progressId, cowValues);

        getRef().updateChildren(childUpdates);
    }

    void getPackageFromId(String aPackageId, ModelListener<Package> callback) {
        getModelFromId(mPackageRef.child(aPackageId), Package.class, callback);
    }

    StorageReference getCowImageFromId(String cowId) {
        return getStrorageRef().child(COW_TREE).child(cowId).child(HEAD_JPG);
    }
}
