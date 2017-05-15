package com.ngadep.fatteningcattle.datasources;

import com.ngadep.fatteningcattle.models.User;

public interface UserDataSourceUpdate extends UserDataSource{

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
