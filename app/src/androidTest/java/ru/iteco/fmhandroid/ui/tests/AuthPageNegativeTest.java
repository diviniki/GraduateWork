package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import android.view.View;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Description;
import ru.iteco.fmhandroid.PasswordFile;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.AuthPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthPageNegativeTest {
    MainPage mainPage = new MainPage();
    AuthPage authPage = new AuthPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);


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

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<AppActivity>() {
            @Override
            public void perform(AppActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
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

    private View decorView;
    String login = PasswordFile.login;
    String password = PasswordFile.password;


    @Description("Негативный сценарий: попытка авторизоваться с пустым логином и паролем") // Не успешная авторизация: попытка входа с пустыми логином и паролем
    @Test
    public void negativeAuthValidLoginTest1() throws InterruptedException {
        Allure.step("Вводим логин: ");
        authPage.enterLogin("");
        Allure.step("Вводим пароль: ");
        authPage.enterPassword("");
        Allure.step("Нажимаем кнопку войти");
        authPage.clickButton();
        Allure.step("Сверяем текст ошибки: " + R.string.empty_login_or_password);
        onView(withText(R.string.empty_login_or_password)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Логин'. Заполнено: пусто") // Не успешная авторизация (валидация логина): пустой логин
    @Test
    public void negativeAuthValidLoginTest2() throws InterruptedException {
        Allure.step("Вводим логин: пусто");
        authPage.enterLogin("");
        Allure.step("Вводим пароль: " + password);
        authPage.enterPassword(password);
        Allure.step("Нажимаем кнопку войти");
        authPage.clickButton();
        Allure.step("Сверяем текст ошибки: " + R.string.empty_login_or_password);
        onView(withText(R.string.empty_login_or_password)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Логин'. Заполнено: пробел")  // Не успешная авторизация (валидация логина): пробел
    @Test
    public void negativeAuthValidLoginTest3() throws InterruptedException {
        Allure.step("Вводим логин: пробел");
        authPage.enterLogin(" ");
        Allure.step("Вводим пароль: " + password);
        authPage.enterPassword(password);
        Allure.step("Нажимаем кнопку войти");
        authPage.clickButton();
        Allure.step("Сверяем текст ошибки: " + R.string.empty_login_or_password);
        onView(withText(R.string.empty_login_or_password)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Логин'. Заполнено: табуляция") // Не успешная авторизация (валидация логина): табуляция
    @Test
    public void negativeAuthValidLoginTest4() throws InterruptedException {
        Allure.step("Вводим логин: табуляция");
        authPage.enterLogin("    ");
        Allure.step("Вводим пароль: " + password);
        authPage.enterPassword(password);
        Allure.step("Нажимаем кнопку войти");
        authPage.clickButton();
        Allure.step("Сверяем текст ошибки: " + R.string.empty_login_or_password);
        onView(withText(R.string.empty_login_or_password)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Логин'. Заполнено: в верхнем регистре") // Не успешная авторизация (валидация логина): в верхнем регистре
    @Test
    public void negativeAuthValidLoginTest5() throws InterruptedException {
        Allure.step("Вводим логин: в верхн.рег.");
        authPage.enterLogin(login.toUpperCase());
        Allure.step("Вводим пароль: " + password);
        authPage.enterPassword(password);
        Allure.step("Нажимаем кнопку войти");
        authPage.clickButton();
        Allure.step("Сверяем текст ошибки: " + R.string.error);
        onView(withText(R.string.error)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Логин'. Заполнено: со спец.символами") // Не успешная авторизация (валидация логина): спец. символ
    @Test
    public void negativeAuthValidLoginTest6() throws InterruptedException {
        Allure.step("Вводим логин: логин + !");
        authPage.enterLogin(login + "!");
        Allure.step("Вводим пароль: " + password);
        authPage.enterPassword(password);
        Allure.step("Нажимаем кнопку войти");
        authPage.clickButton();
        Allure.step("Сверяем текст ошибки: " + R.string.error);
        onView(withText(R.string.error)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Логин'. Заполнено: на кириллице") // Не успешная авторизация (валидация логина): на кириллице
    @Test
    public void negativeAuthValidLoginTest7() throws InterruptedException {
        Allure.step("Вводим логин: на кириллице");
        authPage.enterLogin("Логин2");
        Allure.step("Вводим пароль: " + password);
        authPage.enterPassword(password);
        Allure.step("Нажимаем кнопку войти");
        authPage.clickButton();
        Allure.step("Сверяем текст ошибки: " + R.string.error);
        onView(withText(R.string.error)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Логин'. Заполнено: число") // Не успешная авторизация (валидация логина): число
    @Test
    public void negativeAuthValidLoginTest8() throws InterruptedException {
        Allure.step("Вводим логин: число");
        authPage.enterLogin("123");
        Allure.step("Вводим пароль: " + password);
        authPage.enterPassword(password);
        Allure.step("Нажимаем кнопку войти");
        authPage.clickButton();
        Allure.step("Сверяем текст ошибки: " + R.string.error);
        onView(withText(R.string.error)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Логин'. Заполнено: двойное слово через пробел") // Не успешная авторизация (валидация логина): двойное слово через пробел
    @Test
    public void negativeAuthValidLoginTest9() throws InterruptedException {
        Allure.step("Вводим логин: двойное слово через пробел");
        authPage.enterLogin("login login");
        Allure.step("Вводим пароль: " + password);
        authPage.enterPassword(password);
        Allure.step("Нажимаем кнопку войти");
        authPage.clickButton();
        Allure.step("Сверяем текст ошибки: " + R.string.error);
        onView(withText(R.string.error)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Логин'. Заполнено: Null") // Не успешная авторизация (валидация логина): ввести Null
    @Test
    public void negativeAuthValidLoginTest10() throws InterruptedException {
        Allure.step("Вводим логин: Null");
        authPage.enterLogin("Null");
        Allure.step("Вводим пароль: " + password);
        authPage.enterPassword(password);
        Allure.step("Нажимаем кнопку войти");
        authPage.clickButton();
        Allure.step("Сверяем текст ошибки: " + R.string.error);
        onView(withText(R.string.error)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }



    @Description("Валидация поля 'Пароль'. Заполнено: пусто") // Не успешная авторизация (валидация пароля): пустой пароль
    @Test
    public void negativeAuthValidPasswordTest1() throws InterruptedException {
        Allure.step("Вводим логин: " + login);
        authPage.enterLogin(login);
        Allure.step("Вводим пароль: пусто");
        authPage.enterPassword("");
        Allure.step("Нажимаем кнопку войти");
        authPage.clickButton();
        Allure.step("Сверяем текст ошибки: " + R.string.empty_login_or_password);
        onView(withText(R.string.empty_login_or_password)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Пароль'. Заполнено: пробел") // Не успешная авторизация (валидация пароля): пробел
    @Test
    public void negativeAuthValidPasswordTest2() throws InterruptedException {
        Allure.step("Вводим логин: " + login);
        authPage.enterLogin(login);
        Allure.step("Вводим пароль: пробел");
        authPage.enterPassword(" ");
        Allure.step("Нажимаем кнопку войти");
        authPage.clickButton();
        Allure.step("Сверяем текст ошибки: " + R.string.empty_login_or_password);
        onView(withText(R.string.empty_login_or_password)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Пароль'. Заполнено: табуляция") // Не успешная авторизация (валидация пароля): табуляция
    @Test
    public void negativeAuthValidPasswordTest3() throws InterruptedException {
        Allure.step("Вводим логин: " + login);
        authPage.enterLogin(login);
        Allure.step("Вводим пароль: табуляция");
        authPage.enterPassword("    ");
        Allure.step("Нажимаем кнопку войти");
        authPage.clickButton();
        Allure.step("Сверяем текст ошибки: " + R.string.empty_login_or_password);
        onView(withText(R.string.empty_login_or_password)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Пароль'. Заполнено: в верхнем регистре") // Не успешная авторизация (валидация пароля): в верхнем регистре
    @Test
    public void negativeAuthValidPasswordTest4() throws InterruptedException {
        Allure.step("Вводим логин: " + login);
        authPage.enterLogin(login);
        Allure.step("Вводим пароль: в верхн.рег.");
        authPage.enterPassword(password.toUpperCase());
        Allure.step("Нажимаем кнопку войти");
        authPage.clickButton();
        Allure.step("Сверяем текст ошибки: " + R.string.error);
        onView(withText(R.string.error)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Пароль'. Заполнено: со спец.символами") // Не успешная авторизация (валидация пароля): спец. символ
    @Test
    public void negativeAuthValidPasswordTest5() throws InterruptedException {
        Allure.step("Вводим логин: " + login);
        authPage.enterLogin(login);
        Allure.step("Вводим пароль: пароль + !");
        authPage.enterPassword(password + "!");
        Allure.step("Нажимаем кнопку войти");
        authPage.clickButton();
        Allure.step("Сверяем текст ошибки: " + R.string.error);
        onView(withText(R.string.error)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Пароль'. Заполнено: на кириллице") // Не успешная авторизация (валидация пароля): на кириллице
    @Test
    public void negativeAuthValidPasswordTest6() throws InterruptedException {
        Allure.step("Вводим логин: " + login);
        authPage.enterLogin(login);
        Allure.step("Вводим пароль: на кириллице");
        authPage.enterPassword("Пароль2");
        Allure.step("Нажимаем кнопку войти");
        authPage.clickButton();
        Allure.step("Сверяем текст ошибки: " + R.string.error);
        onView(withText(R.string.error)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Пароль'. Заполнено: число") // Не успешная авторизация (валидация пароля): число
    @Test
    public void negativeAuthValidPasswordTest7() throws InterruptedException {
        Allure.step("Вводим логин: " + login);
        authPage.enterLogin(login);
        Allure.step("Вводим пароль: число");
        authPage.enterPassword("123");
        Allure.step("Нажимаем кнопку войти");
        authPage.clickButton();
        Allure.step("Сверяем текст ошибки: " + R.string.error);
        onView(withText(R.string.error)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Пароль'. Заполнено: двойное слово через пробел") // Не успешная авторизация (валидация пароля): двойное слово через пробел
    @Test
    public void negativeAuthValidPasswordTest8() throws InterruptedException {
        Allure.step("Вводим логин: " + login);
        authPage.enterLogin(login);
        Allure.step("Вводим пароль: двойное слово через пробел");
        authPage.enterPassword("password password");
        Allure.step("Нажимаем кнопку войти");
        authPage.clickButton();
        Allure.step("Сверяем текст ошибки: " + R.string.error);
        onView(withText(R.string.error)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Пароль'. Заполнено: Null") // Не успешная авторизация (валидация пароля): ввести Null
    @Test
    public void negativeAuthValidPasswordTest9() throws InterruptedException {
        Allure.step("Вводим логин: " + login);
        authPage.enterLogin(login);
        Allure.step("Вводим пароль: Null");
        authPage.enterPassword("Null");
        Allure.step("Нажимаем кнопку войти");
        authPage.clickButton();
        Allure.step("Сверяем текст ошибки: " + R.string.error);
        onView(withText(R.string.error)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

}
