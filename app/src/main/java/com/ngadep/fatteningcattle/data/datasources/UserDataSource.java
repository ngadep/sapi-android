package com.ngadep.fatteningcattle.data.datasources;

import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.data.models.User;

public interface UserDataSource {
    Query getUsers();

    User getUserById(String uid);

    String getUserId();

}
