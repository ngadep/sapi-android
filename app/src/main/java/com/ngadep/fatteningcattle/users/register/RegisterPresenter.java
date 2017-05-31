package com.ngadep.fatteningcattle.users.register;

import com.ngadep.fatteningcattle.models.User;
import com.ngadep.fatteningcattle.users.UserRepository;

class RegisterPresenter implements RegisterContract.Presenter {

    private final RegisterContract.View mView;
    private final UserRepository mRepository;

    RegisterPresenter(RegisterContract.View view) {
        mRepository = UserRepository.getInstance();
        mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void saveUser(User user, String password) {
        createUserCallback(user, password);
    }

    private void createUserCallback(User user, String password) {
        mRepository.registerUser(user, password, new UserRepository.RegisterListener() {
            @Override
            public void onRegister(boolean success) {
                if (success) {
                    mView.loginSuccess();
                } else {
                    mView.showLoginFailed();
                }
            }
        });
    }
}
