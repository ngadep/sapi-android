package com.ngadep.fatteningcattle.users;

import com.google.firebase.database.Query;

interface UserView {

    void showAllUser(Query users);

    void startPackageActivity(String userKey);
}
