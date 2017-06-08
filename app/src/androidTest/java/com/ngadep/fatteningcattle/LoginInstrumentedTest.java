package com.ngadep.fatteningcattle;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ngadep.fatteningcattle.users.login.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginInstrumentedTest {

    @Rule
    public ActivityTestRule<LoginActivity> mRule = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void initTest() {
        String mEmail = "saya@gmail.com";
    }
    @Test
    public void tryLogin() throws Exception {

    }
}
