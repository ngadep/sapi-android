package com.ngadep.fatteningcattle.data.datasources;

import com.ngadep.fatteningcattle.data.models.User;

public interface UserDataSourceUpdate extends UserDataSource{

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
