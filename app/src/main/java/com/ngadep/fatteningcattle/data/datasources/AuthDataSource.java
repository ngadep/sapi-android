package com.ngadep.fatteningcattle.data.datasources;

public interface AuthDataSource {

    interface LogInListener {
        void onLogIn(boolean success);
    }

    boolean isLogin();

    void tryLogIn(String email, String password, LogInListener callback);
}
