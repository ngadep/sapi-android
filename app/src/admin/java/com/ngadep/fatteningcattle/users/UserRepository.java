package com.ngadep.fatteningcattle.users;

import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.BaseRepository;

class UserRepository extends BaseRepository{

    private static final String USERS_REF = "users";
    private static UserRepository INSTANCE = null;

    public static UserRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository();
        }
        return INSTANCE;
    }

    Query getUsersQuery() {
        return getRef().child(USERS_REF);
    }
}
