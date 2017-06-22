package com.ngadep.fatteningcattle.cows;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.models.Cow;
import com.ngadep.fatteningcattle.models.Progress;

import java.util.HashMap;
import java.util.Map;

public class CowRepository extends BaseRepository {
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

    public void saveCowProgress(Progress progress) {
        this.saveCowProgress(null, progress);
    }

    public void saveCowProgress(String progressId, Progress progress) {
        DatabaseReference localRef = getRef().child("progresses");

        if (progressId == null) {
            progressId = localRef.push().getKey();
        }

        Map<String, Object> cowValues = progress.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/progresses/" + progressId, cowValues);
        childUpdates.put("/cow-progresses/" + progress.getCow_id() + "/" + progressId, cowValues);

        getRef().updateChildren(childUpdates);
    }
}
