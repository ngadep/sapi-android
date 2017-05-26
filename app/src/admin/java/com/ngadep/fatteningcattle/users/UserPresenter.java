package com.ngadep.fatteningcattle.users;

import com.ngadep.fatteningcattle.data.models.User;

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

    public void startUserPackageActivity(User user) {
        mView.startPackageActivity(user);
    }
}
