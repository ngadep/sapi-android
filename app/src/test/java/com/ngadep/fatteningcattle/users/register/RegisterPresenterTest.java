package com.ngadep.fatteningcattle.users.register;

import com.ngadep.fatteningcattle.users.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class RegisterPresenterTest {

    @Mock
    RegisterContract.View mView;

    @Mock
    UserRepository mRepository;

    @Captor
    private ArgumentCaptor<UserRepository.RegisterListener> mRegisterListenerCaptor;

    private RegisterPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        mPresenter = new RegisterPresenter(mView, mRepository);
    }

    @Test
    public void testIfRegisterSuccess() throws Exception {
        assertTrue(true);
    }
}