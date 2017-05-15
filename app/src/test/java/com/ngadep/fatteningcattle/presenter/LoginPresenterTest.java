package com.ngadep.fatteningcattle.presenter;

import com.ngadep.fatteningcattle.contracts.LoginContract;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginPresenterTest {
    private static final String USER_EMAIL = "testing@gmail.com";
    private static final String USER_PASSWORD = "password";
    private static final String WRONG_USER_PASSWORD = "123456";

    @Mock
    LoginContract.View mView;

    @Mock
    LoginContract.Repository mRepository;

    private LoginPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mPresenter = new LoginPresenter(mView, mRepository);

    }

    @Test
    public void testStart_LoginSuccess() {
        when(mRepository.isLogin()).thenReturn(true);
        mPresenter.start();
        verify(mRepository).isLogin();
        verify(mView).startMainActivity();
    }

    @Test
    public void testStart_LoginFailed() {
        when(mRepository.isLogin()).thenReturn(false);
        mPresenter.start();
        verify(mRepository).isLogin();
        verify(mView, never()).startMainActivity();
    }

    @Test
    public void testTryLogIn_Success() {
        when(mRepository.tryLogIn(USER_EMAIL, USER_PASSWORD)).thenReturn(true);

        mPresenter.tryToLogIn(USER_EMAIL, USER_PASSWORD);

        //create an inOrder verifier for a single mock
        InOrder iOrder = inOrder(mView);

        iOrder.verify(mView).showProgressDialog();

        verify(mRepository).tryLogIn(USER_EMAIL, USER_PASSWORD);

        iOrder.verify(mView).hideProgressDialog();
        iOrder.verify(mView).startMainActivity();
    }

    @Test
    public void testTryLogIn_Failed() {
        when(mRepository.tryLogIn(USER_EMAIL, WRONG_USER_PASSWORD)).thenReturn(false);

        mPresenter.tryToLogIn(USER_EMAIL, WRONG_USER_PASSWORD);

        //create an inOrder verifier for a single mock
        InOrder iOrder = inOrder(mView);

        iOrder.verify(mView).showProgressDialog();

        verify(mRepository).tryLogIn(USER_EMAIL, WRONG_USER_PASSWORD);

        iOrder.verify(mView).hideProgressDialog();
        iOrder.verify(mView).showErrorText();
    }
}