package com.ngadep.fatteningcattle.users;

import com.ngadep.fatteningcattle.models.User;

class UserPresenter {

    private final UserView mView;
    private final UserRepository mRepository;

    UserPresenter(UserView view) {
        mView = view;
        mRepository = UserRepository.getInstance();
    }

    void start() {
        mView.showAllUser(mRepository.getUsersQuery());
    }

    void startUserPackageActivity(String userId, User user) {
        mView.startPackageActivity(userId, user);
    }
}
