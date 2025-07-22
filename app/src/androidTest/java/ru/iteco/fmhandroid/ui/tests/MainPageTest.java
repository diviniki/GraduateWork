package ru.iteco.fmhandroid.ui.tests;

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
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.EspressoIdlingResources;
import ru.iteco.fmhandroid.PasswordFile;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.AboutPage;
import ru.iteco.fmhandroid.ui.pages.AuthPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage;
import ru.iteco.fmhandroid.ui.pages.OurMissionPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class MainPageTest {
    MainPage mainPage = new MainPage();
    AuthPage authPage = new AuthPage();
    NewsPage newsPage = new NewsPage();
    AboutPage aboutPage = new AboutPage();
    OurMissionPage ourMissionPagePage = new OurMissionPage();

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
            Thread.sleep(2800);
            authPage.checkLoadPage();
            authPage.toLogIn();
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
        Allure.step("Проверяем, что открылась главная страница");
        mainPage.checkLogo();
    }


    @Description("Проверка перехода на страницу 'О приложении'") //"Переход на страницу 'О приложении'"
    @Test
    public void goToAboutPageTest() {
        Allure.step("Переходим на страницу: О приложении");
        mainPage.goToAboutPage();
        Allure.step("Ищем на странице слово: Версия");
        aboutPage.checkVersion();
    }

    @Description("Проверка перехода на страницу 'Новости'") //"Переход на страницу 'Новости' через меню"
    @Test
    public void goToNewsPageTest() {
        Allure.step("Переходим на страницу: Новости");
        mainPage.goToNewsPage();
        Allure.step("Проверяем, что страница открылась");
        newsPage.editNewsButton();
    }

    @Description("Проверка перехода на страницу 'Все новости'")  //"Переход на страницу 'Новости' через кнопку 'Все новости'"
    @Test
    public void goToAllNewsPageTest() {
        Allure.step("Переходим на страницу: Все новости");
        mainPage.goToAllNewsPage();
        Allure.step("Проверяем, что страница открылась");
        newsPage.editNewsButton();
    }

    @Description("Проверка перехода на страницу 'Наша миссия'") //"Переход на страницу 'Наша миссия'"
    @Test
    public void goToMissionPageTest() {
        Allure.step("Переходим на страницу: Наша миссия");
        mainPage.goToMissionPage();
        Allure.step("Проверяем, что страница открылась");
        ourMissionPagePage.checkTitle();
    }

    @Description("Проверка кнопки свернуть/развернуть список новостей")  //"Свернуть раздел новостей и убедиться что список исчез"
    @Test
    public void expandMaterialTest() {
        Allure.step("Сворачиваем новости и проверяем, что они больше не отображаются на экране");
        mainPage.expandMaterial();
    }

    @Description("Деавторизация пользователя")  //"Выход из профиля"
    @Test
    public void toLogOutTest() {
        Allure.step("Выходим из профиля");
        mainPage.toLogOut();
        Allure.step("Проверяем, что открылась страница авторизации");
        authPage.checkLoadPage();
    }
}
