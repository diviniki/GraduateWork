package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.pages.AuthPage.checkLoadPage;
import static ru.iteco.fmhandroid.ui.pages.AuthPage.toLogIn;
import static ru.iteco.fmhandroid.ui.pages.MainPage.checkLogo;
import static ru.iteco.fmhandroid.ui.pages.MainPage.goToMissionPage;
import static ru.iteco.fmhandroid.ui.pages.OurMissionPage.checkTitle;
import static ru.iteco.fmhandroid.ui.pages.OurMissionPage.goBackToMainPage;

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
import io.qameta.allure.kotlin.Description;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class OurMissionPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void tryLogIn() throws InterruptedException {
        try {
            Thread.sleep(2500);
            checkLoadPage();
            toLogIn();
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
        goToMissionPage();
        checkTitle();
    }

    @Description("Проверка перехода обратно на главную страницу") //"Вернуться на главную"
    @Test
    public void goBackToMainPageTest() {
        goToMissionPage();
        goBackToMainPage();
        checkLogo();
    }
}
