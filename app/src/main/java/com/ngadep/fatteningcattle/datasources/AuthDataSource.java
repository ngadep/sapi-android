package com.ngadep.fatteningcattle.datasources;

public interface AuthDataSource {

    boolean isLogin();

    boolean tryLogIn(String email, String password);
}
