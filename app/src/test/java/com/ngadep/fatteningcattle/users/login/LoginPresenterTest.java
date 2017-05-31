package com.ngadep.fatteningcattle.users.login;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.eq;

public class LoginPresenterTest {
    private static final String USER_EMAIL = "testing@gmail.com";
    private static final String USER_PASSWORD = "password";
    private static final String WRONG_USER_PASSWORD = "123456";

    @Mock
    LoginContract.View mView;

    @Mock
    LoginRepository mRepository;

    /**
     * {@link ArgumentCaptor} is a powerful Mockito API to capture argument values and use them to
     * perform further actions or assertions on them.
     */
    @Captor
    private ArgumentCaptor<LoginRepository.LogInListener> mLogInListenerCaptor;

    private LoginPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mPresenter = new LoginPresenter(mView, mRepository);

    }

    @Test
    public void testStart_LoginSuccess() {
        Mockito.when(mRepository.isLogin()).thenReturn(true);
        mPresenter.start();
        Mockito.verify(mRepository).isLogin();
        Mockito.verify(mView).startMainActivity();
    }

    @Test
    public void testStart_LoginFailed() {
        Mockito.when(mRepository.isLogin()).thenReturn(false);
        mPresenter.start();
        Mockito.verify(mRepository).isLogin();
        Mockito.verify(mView, Mockito.never()).startMainActivity();
    }

    @Test
    public void testTryLogIn_Success() {
        mPresenter.tryToLogIn(USER_EMAIL, USER_PASSWORD);

        //create an inOrder verifier for a single mock
        InOrder iOrder = Mockito.inOrder(mView);

        iOrder.verify(mView).showProgressDialog();

        Mockito.verify(mRepository).tryLogIn(eq(USER_EMAIL), eq(USER_PASSWORD), mLogInListenerCaptor.capture());
        mLogInListenerCaptor.getValue().onLogIn(true);

        iOrder.verify(mView).hideProgressDialog();
        iOrder.verify(mView).startMainActivity();
    }

    @Test
    public void testTryLogIn_Failed() {
        mPresenter.tryToLogIn(USER_EMAIL, WRONG_USER_PASSWORD);

        //create an inOrder verifier for a single mock
        InOrder iOrder = Mockito.inOrder(mView);

        iOrder.verify(mView).showProgressDialog();

        Mockito.verify(mRepository).tryLogIn(eq(USER_EMAIL), eq(WRONG_USER_PASSWORD), mLogInListenerCaptor.capture());
        mLogInListenerCaptor.getValue().onLogIn(false);

        iOrder.verify(mView).hideProgressDialog();
        iOrder.verify(mView).showErrorText();
    }
}