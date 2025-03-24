package com.example.zalotest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import kotlin.jvm.Throws;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> MainActivity = new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void  clickLoginButton_opensLoginUi() {
        onView(withId(R.id.btnDangNhap));
    }
    @Test
    public void openApp() {
        onView(withId(R.id.btnDangNhap)).perform(ViewActions.click());
        onView(withId(R.id.main)).check(matches(isDisplayed()));
    }
    @Test
    public void KiemTraDaVaoApp() {
        // Nhấn vào button
        onView(withId(R.id.btnDangNhap)).perform(ViewActions.click());

        // Kiểm tra xem Activity mới có chứa TextView như mong đợi không
        onView(withId(R.id.txtThongbao))
                .check(matches(withText("Vui lòng nhập số điện thoại và mật khẩu để đăng nhập")));
    }
}
