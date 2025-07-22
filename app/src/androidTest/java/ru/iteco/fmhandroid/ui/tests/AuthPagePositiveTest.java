package ru.iteco.fmhandroid.ui.tests;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Description;
import ru.iteco.fmhandroid.EspressoIdlingResources;
import ru.iteco.fmhandroid.PasswordFile;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.AuthPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthPagePositiveTest {
    MainPage mainPage = new MainPage();
    AuthPage authPage = new AuthPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void pauseUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);
    }

    @Before
    public void tryLogOut() throws InterruptedException {
        try {
            Thread.sleep(2800);
            mainPage.checkLogo();
            mainPage.toLogOut();
        }
        catch (Error e) {
        }
    }


    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource);
    }


    @BeforeClass
    public static void disableAnimations() {
        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand("settings put global window_animation_scale 0");
        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand("settings put global transition_animation_scale 0");
        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand("settings put global animator_duration_scale 0");
    }

    @AfterClass
    public static void enableAnimations() {
        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand("settings put global window_animation_scale 1");
        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand("settings put global transition_animation_scale 1");
        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand("settings put global animator_duration_scale 1");
    }

    String login = PasswordFile.login;
    String password = PasswordFile.password;


    @Description("Проверка отображения страницы") //Проверяем, что страница загружается и отображается Авторизация
    @Test
    public void checkTitleAtAuthPageTest() {
        authPage.checkLoadPage();
    }

    @Description("Позитивный сценарий: успешная авторизация") // Успешная авторизация
    @Test
    public void positiveAuthTest() throws InterruptedException {
        Allure.step("Вводим логин: " + login);
        authPage.enterLogin(login);
        Allure.step("Вводим пароль: " + password);
        authPage.enterPassword(password);
        Allure.step("Нажимаем кнопку войти");
        authPage.clickButton();
        Allure.step("Проверяем, что открылась главная страница");
        mainPage.checkLogo();
    }

}
