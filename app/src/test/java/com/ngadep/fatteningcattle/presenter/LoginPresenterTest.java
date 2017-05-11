package com.ngadep.fatteningcattle.presenter;

import com.ngadep.fatteningcattle.R;
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
        mPresenter.onLoginResult(LoginPresenter.LogInStatus.CANCELLED);
        verify(mView).showTextAndButton(true);
        verify(mView).showErrorText(R.string.sign_in_cancelled);
    }

    @Test
    public void testOnLoginResult_NoNetwork() {
        mPresenter.onLoginResult(LoginPresenter.LogInStatus.NO_NETWORK);
        verify(mView).showTextAndButton(true);
        verify(mView).showErrorText(R.string.sign_in_no_network);
    }

    @Test
    public void testOnLoginResult_UnKnownError() {
        mPresenter.onLoginResult(LoginPresenter.LogInStatus.UNKNOWN_ERROR);
        verify(mView).showTextAndButton(true);
        verify(mView).showErrorText(R.string.sign_in_unknown_error);
    }
}