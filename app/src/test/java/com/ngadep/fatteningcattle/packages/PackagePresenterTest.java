package com.ngadep.fatteningcattle.packages;

import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.models.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class PackagePresenterTest {
    private static final String USER_NAME = "Testing App";
    private static final String USER_EMAIL = "testing@gmail.com";

    // random user id
    private static final java.lang.String USER_ID = "lkj234jk9j9";
    private PackagePresenter mPresenter;

    @Mock
    private PackageContract.View mView;

    @Mock
    private PackageRepository mRepository;

    @Captor
    private ArgumentCaptor<BaseRepository.ModelListener<User>> mModelListenerCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreatePresenter_setsThePresenterToView() {
        mPresenter = new PackagePresenter(null, mView, mRepository);
        verify(mView).setPresenter(mPresenter);
    }

    @Test
    public void testStartPresenter_fromCurrentUser() {
        mPresenter = new PackagePresenter(null, mView, mRepository);
        mPresenter.start();

        verify(mRepository).getUid();

        verify(mRepository).getPackagesFromUserId(null);
        verify(mView).getPackages(mRepository.getPackagesFromUserId(null));
    }

    @Test
    public void testStartPresenter_fromSelectedUser() {
        mPresenter = new PackagePresenter(USER_ID, mView, mRepository);
        mPresenter.start();

        User user = new User(USER_NAME, USER_EMAIL);

        Mockito.verify(mRepository).getUserFromId(eq(USER_ID), mModelListenerCaptor.capture());
        mModelListenerCaptor.getValue().onModelChange(user);

        mView.notifyUserChange(user);

        verify(mRepository).getPackagesFromUserId(USER_ID);
        verify(mView).getPackages(mRepository.getPackagesFromUserId(USER_ID));
    }

    @Test
    public void testShow_CowActivity() {
        mPresenter = new PackagePresenter(USER_ID, mView, mRepository);
        String packageKey = "1";
        mPresenter.showCowActivity(packageKey);
        verify(mView).startCowActivity(packageKey);
    }

    @Test
    public void testShow_EditPackageActivity() {
        mPresenter = new PackagePresenter(USER_ID, mView, mRepository);
        mPresenter.showEditPackageActivity();
        verify(mView).startEditPackageActivity(USER_ID);
    }

}