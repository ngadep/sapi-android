package com.ngadep.fatteningcattle.presenter;

import com.ngadep.fatteningcattle.contracts.LoginContract;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class LoginPresenterTest {
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
    public void testTryToLogIn() {
        mPresenter.tryToLogIn();
        verify(mView).tryLogIn();
    }

    @Test
    public void testTryLogIn_Success() {
        when(mRepository.isLogin()).thenReturn(true);

        mPresenter.start();

        verify(mView).startMainActivity();
    }

    @Test
    public void testTryLogIn_Failed() {
        when(mRepository.isLogin()).thenReturn(false);

        mPresenter.start();

        verify(mView).tryLogIn();
    }

    @Test
    public void testOnLoginResult_Success() {
        mPresenter.onLoginResult(-1);
        verify(mView).showTextAndButton(false);
        verify(mView).startMainActivity();
    }

    @Test
    public void testOnLoginResult_Cancelled() {
        mPresenter.onLoginResult(0);
        verify(mView).showTextAndButton(true);
        verify(mView).showLoginFailed(0);
    }

    @Test
    public void testOnLoginResult_NoNetwork() {
        mPresenter.onLoginResult(10);
        verify(mView).showTextAndButton(true);
        verify(mView).showLoginFailed(10);
    }

    @Test
    public void testOnLoginResult_UnKnownError() {
        mPresenter.onLoginResult(20);
        verify(mView).showTextAndButton(true);
        verify(mView).showLoginFailed(20);
    }
}