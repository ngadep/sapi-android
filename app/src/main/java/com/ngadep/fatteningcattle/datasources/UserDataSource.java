package com.ngadep.fatteningcattle.datasources;

import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.models.User;

public interface UserDataSource {
    Query getUsers();

    User getUserById(String uid);

    String getUserId();

}
