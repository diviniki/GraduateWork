package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.pages.AboutPage.checkVersion;
import static ru.iteco.fmhandroid.ui.pages.AuthPage.*;
import static ru.iteco.fmhandroid.ui.pages.MainPage.*;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.editNewsButton;
import static ru.iteco.fmhandroid.ui.pages.OurMissionPage.checkTitle;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import android.view.View;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.EspressoIdlingResources;
import ru.iteco.fmhandroid.PasswordFile;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.AuthPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class MainPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before // Выполняется перед тестами
    public void pauseUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);
    }


//    @Before // Выполняется перед тестами
//    public void pauseUp() {
//        try {
//            Thread.sleep(2500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

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

    @Description("Проверка отображения страницы") //"Проверяем, что страница загружается"
    @Test
    public void checkLogoAtMainPageTest() {
        checkLogo();
    }


    @Description("Проверка перехода на страницу 'О приложении'") //"Переход на страницу 'О приложении'"
    @Test
    public void goToAboutPageTest() {
        goToAboutPage();
        checkVersion();
    }

    @Description("Проверка перехода на страницу 'Новости'") //"Переход на страницу 'Новости' через меню"
    @Test
    public void goToNewsPageTest() {
        goToNewsPage();
        editNewsButton();
    }

    @Description("Проверка перехода на страницу 'Все новости'")  //"Переход на страницу 'Новости' через кнопку 'Все новости'"
    @Test
    public void goToAllNewsPageTest() {
        goToAllNewsPage();
        editNewsButton();
    }

    @Description("Проверка перехода на страницу 'Наша миссия'") //"Переход на страницу 'Наша миссия'"
    @Test
    public void goToMissionPageTest() {
        goToMissionPage();
        checkTitle();
    }

    @Description("Проверка кнопки свернуть/развернуть список новостей")  //"Свернуть раздел новостей и убедиться что список исчез"
    @Test
    public void expandMaterialTest() {
        expandMaterial();
    }

    @Description("Деавторизация пользователя")  //"Выход из профиля"
    @Test
    public void toLogOutTest() {
        toLogOut();
        checkLoadPage();
    }
}
