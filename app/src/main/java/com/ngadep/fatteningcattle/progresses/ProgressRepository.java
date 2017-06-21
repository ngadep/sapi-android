package com.ngadep.fatteningcattle.progresses;

import com.google.firebase.database.DatabaseReference;
import com.ngadep.fatteningcattle.BaseRepository;

class ProgressRepository extends BaseRepository {
    private static ProgressRepository INSTANCE = null;
    private final DatabaseReference mRef;

    private ProgressRepository() {
        super();
        mRef = getRef().child("cow-progresses");
    }

    public static ProgressRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProgressRepository();
        }

        return INSTANCE;
    }
}
