package ru.iteco.fmhandroid.ui.pages;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import androidx.test.espresso.ViewInteraction;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;


import static ru.iteco.fmhandroid.DataValues.text_in_page_title;
import static ru.iteco.fmhandroid.PasswordFile.login;
import static ru.iteco.fmhandroid.PasswordFile.password;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.BaseFunctions;

public class AuthPage extends BaseFunctions {
    private static int nav_host_fragment = R.id.nav_host_fragment;
    private static int login_text_input_layout = R.id.login_text_input_layout;
    private static int password_text_input_layout = R.id.password_text_input_layout;
    private static int enter_button = R.id.enter_button;

    static String hint_login_text_input_layout = "Логин";
    static String hint_password_text_input_layout = "Пароль";

    // "Ищем title страницы"
    public void checkLoadPage() {
        ViewInteraction textTitle = onView(allOf(withText(text_in_page_title), withParent(withParent(withId(nav_host_fragment)))));
        checkObjectDisplayed(textTitle);
        checkTextInField(textTitle, text_in_page_title);
    }

    // "Проверяем наличие поля Логин на странице"
    public void checkLoginField() {
        ViewInteraction textInputLogin = onView(allOf(withHint(hint_login_text_input_layout), withParent(withParent(withId(login_text_input_layout)))));
        checkObjectDisplayed(textInputLogin);
    }

    // "Ищем и заполняем поле логин"
    public void enterLogin(String login) {
        ViewInteraction textInputLogin = onView(allOf(withHint(hint_login_text_input_layout), withParent(withParent(withId(login_text_input_layout)))));
        checkObjectDisplayed(textInputLogin);
        enterTextIntoTheFiled(textInputLogin, login);
    }

    // "Ищем и заполняем поле пароль"
    public void enterPassword(String password) {
        ViewInteraction textInputPassword = onView(allOf(withHint(hint_password_text_input_layout), withParent(withParent(withId(password_text_input_layout)))));
        checkObjectDisplayed(textInputPassword);
        enterTextIntoTheFiled(textInputPassword, password);
    }

    // "Ищем и кликаем по кнопке войти"
    public void clickButton() {
        ViewInteraction materialButton = onView(withId(enter_button));
        clickByButton(materialButton);
    }

    // "Проверяем наличие кнопки на странице"
    public void checkLoginButton() {
        ViewInteraction materialButton = onView(withId(enter_button));
        checkObjectDisplayed(materialButton);
    }

    public void toLogIn () {
        enterLogin(login);
        enterPassword(password);
        clickButton();
    }

}
