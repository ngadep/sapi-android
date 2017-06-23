package com.ngadep.fatteningcattle.progresses;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.models.Cow;
import com.ngadep.fatteningcattle.models.Progress;

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
}
