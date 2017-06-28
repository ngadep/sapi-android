package com.ngadep.fatteningcattle.users;

class UserPresenter implements UserContract.Presenter {

    private final UserContract.View mView;
    private final UserRepository mRepository;

    UserPresenter(UserContract.View view, UserRepository repository) {
        mView = view;
        mRepository = repository;

        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.showAllUser(mRepository.getUsersQuery());
    }

    @Override
    public void showPackageActivity(String userId) {
        mView.startPackageActivity(userId);
    }
}
