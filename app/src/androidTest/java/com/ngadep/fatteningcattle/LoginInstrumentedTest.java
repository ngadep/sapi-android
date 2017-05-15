package com.ngadep.fatteningcattle;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ngadep.fatteningcattle.activities.LogInActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class LoginInstrumentedTest {

    private String mEmail;

    @Rule
    public ActivityTestRule<LogInActivity> mRule = new ActivityTestRule<>(LogInActivity.class);

    @Before
    public void initTest() {
        mEmail = "saya@gmail.com";
    }
    @Test
    public void tryLogin() throws Exception {

    }
}
