package com.ngadep.fatteningcattle.users.register;

import com.ngadep.fatteningcattle.models.User;
import com.ngadep.fatteningcattle.users.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.eq;

public class RegisterPresenterTest {

    private static final String USER_EMAIL = "testing@gmail.com";
    private static final String USER_PASSWORD = "password";

    @Mock
    RegisterContract.View mView;

    @Mock
    UserRepository mRepository;

    @Captor
    private ArgumentCaptor<UserRepository.RegisterListener> mRegisterListenerCaptor;

    private RegisterPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mPresenter = new RegisterPresenter(mView, mRepository);
    }

    @Test
    public void testRegisterSuccess() {
        User user = new User(USER_EMAIL, USER_PASSWORD);
        mPresenter.saveUser(user, USER_PASSWORD);

        Mockito.verify(mRepository).registerUser(eq(user), eq(USER_PASSWORD), mRegisterListenerCaptor.capture());
        mRegisterListenerCaptor.getValue().onRegister(true);
        Mockito.verify(mView).registerSuccess();
    }

    @Test
    public void testRegisterFailed() {
        User user = new User(USER_EMAIL, USER_PASSWORD);
        mPresenter.saveUser(user, USER_PASSWORD);

        Mockito.verify(mRepository).registerUser(eq(user), eq(USER_PASSWORD), mRegisterListenerCaptor.capture());
        mRegisterListenerCaptor.getValue().onRegister(false);
        Mockito.verify(mView).showRegisterFailed();
    }

}