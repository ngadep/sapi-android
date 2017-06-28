package com.ngadep.fatteningcattle.users;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class UserPresenterTest {

    private UserPresenter mPresenter;

    @Mock
    private UserContract.View mView;

    @Mock
    private UserRepository mRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mPresenter = new UserPresenter(mView, mRepository);
    }

    @Test
    public void testSetViewOnCreatePresenter() {
        verify(mView).setPresenter(mPresenter);
    }

    @Test
    public void testStartPresenter() {
        mPresenter.start();
        verify(mView).showAllUser(mRepository.getUsersQuery());
    }

    @Test
    public void testShowPackageActivity() {
        String userId = "123";
        mPresenter.showPackageActivity(userId);
        verify(mView).startPackageActivity(userId);
    }

}