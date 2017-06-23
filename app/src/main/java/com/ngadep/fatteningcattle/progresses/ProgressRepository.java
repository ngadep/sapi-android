package com.ngadep.fatteningcattle.progresses;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.models.Cow;
import com.ngadep.fatteningcattle.models.Progress;

import java.util.HashMap;
import java.util.Map;

public class ProgressRepository extends BaseRepository {
    private final String COW_TREE = "cows";
    private final String COW_PROGRESS_TREE = "cow-progresses";
    private final String PROGRESS_TREE = "progresses";

    private static ProgressRepository INSTANCE = null;
    private final DatabaseReference mCowRef;
    private final DatabaseReference mCowProgressesRef;
    private final DatabaseReference mProgressRef;


    private ProgressRepository() {
        super();
        mCowRef = getRef().child(COW_TREE);
        mCowProgressesRef = getRef().child(COW_PROGRESS_TREE);
        mProgressRef = getRef().child(PROGRESS_TREE);
    }

    public static ProgressRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProgressRepository();
        }

        return INSTANCE;
    }

    public Query getCowProgressFromId(String cowId) {
        return mCowProgressesRef.child(cowId);
    }

    public void getCowModelFromId(String cowId, ModelListener<Cow> callback) {
        getModelFromId(mCowRef.child(cowId), Cow.class, callback);
    }

    public void getProgressFromId(String progressId, ModelListener<Progress> callback) {
        getModelFromId(mProgressRef.child(progressId), Progress.class, callback);
    }

    public void saveProgress(Progress progress) {
        this.saveProgress(null, progress);
    }

    public void saveProgress(@Nullable String progressId, Progress progress) {
        if (progressId == null) {
            progressId = mProgressRef.push().getKey();
        }

        Map<String, Object> progressValues = progress.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/progresses/" + progressId, progressValues);
        childUpdates.put("/cow-progresses/" + progress.getCow_id() + "/" + progressId, progressValues);

        getRef().updateChildren(childUpdates);
    }

    public void updateCow(@NonNull String aCowId, Cow cow) {
        Map<String, Object> cowValues = cow.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/cows/" + aCowId, cowValues);
        childUpdates.put("/package-cows/" + cow.getPackage_id() + "/" + aCowId, cowValues);

        getRef().updateChildren(childUpdates);

    }
}
