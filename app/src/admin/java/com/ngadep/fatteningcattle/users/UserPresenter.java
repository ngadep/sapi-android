package com.ngadep.fatteningcattle.users;

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

    public void startUserPackageActivity(String userKey) {
        mView.startPackageActivity(userKey);
    }
}
