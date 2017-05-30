package com.ngadep.fatteningcattle.users.edit;

import android.support.annotation.Nullable;

import com.ngadep.fatteningcattle.data.models.User;
import com.ngadep.fatteningcattle.users.UserRepository;

public class EditUserPresenter implements EditUserContract.Presenter {

    private final String mUserId;
    private final User mUser;
    private final EditUserContract.View mView;
    private final UserRepository mRepository;

    public EditUserPresenter(@Nullable String userId, @Nullable User user, EditUserContract.View view) {
        mRepository = UserRepository.getInstance();
        mUserId = userId;
        mUser = user;
        mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void start() {
        if (!newUser()) {
            mView.showUser(mUser);
        }
    }

    private boolean newUser() {
        return mUserId == null;
    }

    @Override
    public void saveUser(User user, String password) {
        if (newUser()) {
            createUser(user, password);
        } else {
            if (password.isEmpty()) {
                updateUser(user);
            } else {
                updateUserAndPassword(user, password);
            }
        }
    }

    private void updateUserAndPassword(User user, String password) {

    }

    private void updateUser(User user) {

    }

    private void createUser(User user, String password) {
        mRepository.saveUser(user, password);
    }
}
