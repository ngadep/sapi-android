package com.ngadep.fatteningcattle.users;

class UserPresenter {

    private final UserView mView;
    private final UserRepository mRepository;

    public UserPresenter(UserView view) {
        mView = view;
        mRepository = UserRepository.getInstance();
    }

    public void start() {

    }
}
