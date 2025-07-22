package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Description;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.AuthPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.pages.OurMissionPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class OurMissionPageTest {
    MainPage mainPage = new MainPage();
    OurMissionPage ourMissionPagePage = new OurMissionPage();
    AuthPage authPage = new AuthPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void tryLogIn() throws InterruptedException {
        try {
            Thread.sleep(2800);
            authPage.checkLoadPage();
            authPage.toLogIn();
        }
        catch (Exception e) {
        }
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

    @Description("Проверка отображения страницы") //"Проверяем, что страница загружается"
    @Test
    public void checkTitleAtOurMissionPageTest() {
        Allure.step("Переходим на страницу: Наша миссия");
        mainPage.goToMissionPage();
        Allure.step("Проверяем, что страница открылась");
        ourMissionPagePage.checkTitle();
    }

    @Description("Проверка перехода обратно на главную страницу") //"Вернуться на главную"
    @Test
    public void goBackToMainPageTest() {
        Allure.step("Переходим на страницу: Наша миссия");
        mainPage.goToMissionPage();
        Allure.step("Переходим обратно на страницу: Главная");
        ourMissionPagePage.goBackToMainPage();
        Allure.step("Проверяем, что страница Главная открылась");
        mainPage.checkLogo();
    }
}
