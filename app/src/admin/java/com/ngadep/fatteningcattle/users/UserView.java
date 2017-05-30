package com.ngadep.fatteningcattle.users;

import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.data.models.User;

interface UserView {

    void showAllUser(Query users);

    void startPackageActivity(String userId, User user);

    void startAddUserUi();
}
