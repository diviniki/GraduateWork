package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.DataValues.current_date;
import static ru.iteco.fmhandroid.DataValues.current_time;
import static ru.iteco.fmhandroid.DataValues.news_item_category;
import static ru.iteco.fmhandroid.DataValues.news_item_description;
import static ru.iteco.fmhandroid.DataValues.news_item_title;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
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
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.AuthPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class ValidationNewsFormTest {
    MainPage mainPage = new MainPage();
    NewsPage newsPage = new NewsPage();
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

    @Description("Валидация поля 'Категория'. Заполнено: пусто") //"Поле категория заполнено: пусто"
    @Test
    public void validationCategoryFieldNegativeTest1() {
        Allure.step("Переходим на страницу: Все новости");
        mainPage.goToAllNewsPage();
        Allure.step("Переходим на страницу: панель управления");
        newsPage.toEditNewsButton();
        Allure.step("Нажимаем на кнопку: создать публикацию");
        newsPage.toCreateNewsButton();
        Allure.step("Заполняем поле: категория; " + "Значением: пусто");
        newsPage.enterCategory("");
        Allure.step("Заполняем поле: название; " + "Значением: " + news_item_title);
        newsPage.enterTitle(news_item_title);
        Allure.step("Заполняем поле дата значением: " + current_date);
        newsPage.enterDate(current_date);
        Allure.step("Заполняем поле время значением: " + current_time);
        newsPage.enterTime(current_time);
        Allure.step("Заполняем поле: описание; " + "Значением: " + news_item_description);
        newsPage.enterDescription(news_item_description);
        Allure.step("Сохраняем публикацию");
        newsPage.savePublication();
        Allure.step("Сверяем текст ошибки: " + R.string.empty_fields);
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Категория'. Заполнено: пробел") //"Поле категория заполнено: пробел"
    @Test
    public void validationCategoryFieldNegativeTest2() {
        Allure.step("Переходим на страницу: Все новости");
        mainPage.goToAllNewsPage();
        Allure.step("Переходим на страницу: панель управления");
        newsPage.toEditNewsButton();
        Allure.step("Нажимаем на кнопку: создать публикацию");
        newsPage.toCreateNewsButton();
        Allure.step("Заполняем поле: категория; " + "Значением: пробел");
        newsPage.enterCategory(" ");
        Allure.step("Заполняем поле: название; " + "Значением: " + news_item_title);
        newsPage.enterTitle(news_item_title);
        Allure.step("Заполняем поле дата значением: " + current_date);
        newsPage.enterDate(current_date);
        Allure.step("Заполняем поле время значением: " + current_time);
        newsPage.enterTime(current_time);
        Allure.step("Заполняем поле: описание; " + "Значением: " + news_item_description);
        newsPage.enterDescription(news_item_description);
        Allure.step("Сохраняем публикацию");
        newsPage.savePublication();
        Allure.step("Сверяем текст ошибки: " + R.string.empty_fields);
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Категория'. Заполнено: в верхнем регистре") //"Поле категория заполнено: в верхнем регистре"
    @Test
    public void validationCategoryFieldNegativeTest3() {
        Allure.step("Переходим на страницу: Все новости");
        mainPage.goToAllNewsPage();
        Allure.step("Переходим на страницу: панель управления");
        newsPage.toEditNewsButton();
        Allure.step("Нажимаем на кнопку: создать публикацию");
        newsPage.toCreateNewsButton();
        Allure.step("Заполняем поле: категория; " + "Значением: в верхн.рег.");
        newsPage.enterCategory(news_item_category.toUpperCase());
        Allure.step("Заполняем поле: название; " + "Значением: " + news_item_title);
        newsPage.enterTitle(news_item_title);
        Allure.step("Заполняем поле дата значением: " + current_date);
        newsPage.enterDate(current_date);
        Allure.step("Заполняем поле время значением: " + current_time);
        newsPage.enterTime(current_time);
        Allure.step("Заполняем поле: описание; " + "Значением: " + news_item_description);
        newsPage.enterDescription(news_item_description);
        Allure.step("Сохраняем публикацию");
        newsPage.savePublication();
        Allure.step("Сверяем текст ошибки: " + R.string.error_saving);
        onView(withText(R.string.error_saving)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Категория'. Заполнено: произвольный текст на кириллице") //"Поле категория заполнено: произвольный текст"
    @Test
    public void validationCategoryFieldNegativeTest4() {
        Allure.step("Переходим на страницу: Все новости");
        mainPage.goToAllNewsPage();
        Allure.step("Переходим на страницу: панель управления");
        newsPage.toEditNewsButton();
        Allure.step("Нажимаем на кнопку: создать публикацию");
        newsPage.toCreateNewsButton();
        Allure.step("Заполняем поле: категория; " + "Значением: произв.текст на кириллице");
        newsPage.enterCategory("МояКатегория");
        Allure.step("Заполняем поле: название; " + "Значением: " + news_item_title);
        newsPage.enterTitle(news_item_title);
        Allure.step("Заполняем поле дата значением: " + current_date);
        newsPage.enterDate(current_date);
        Allure.step("Заполняем поле время значением: " + current_time);
        newsPage.enterTime(current_time);
        Allure.step("Заполняем поле: описание; " + "Значением: " + news_item_description);
        newsPage.enterDescription(news_item_description);
        Allure.step("Сохраняем публикацию");
        newsPage.savePublication();
        Allure.step("Сверяем текст ошибки: " + R.string.error_saving);
        onView(withText(R.string.error_saving)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Категория'. Заполнено: со спец.символами") //"Поле категория заполнено: со спец.символами"
    @Test
    public void validationCategoryFieldNegativeTest5() {
        Allure.step("Переходим на страницу: Все новости");
        mainPage.goToAllNewsPage();
        Allure.step("Переходим на страницу: панель управления");
        newsPage.toEditNewsButton();
        Allure.step("Нажимаем на кнопку: создать публикацию");
        newsPage.toCreateNewsButton();
        Allure.step("Заполняем поле: категория; " + "Значением: " + news_item_category + "!");
        newsPage.enterCategory(news_item_category + "!");
        Allure.step("Заполняем поле: название; " + "Значением: " + news_item_title);
        newsPage.enterTitle(news_item_title);
        Allure.step("Заполняем поле дата значением: " + current_date);
        newsPage.enterDate(current_date);
        Allure.step("Заполняем поле время значением: " + current_time);
        newsPage.enterTime(current_time);
        Allure.step("Заполняем поле: описание; " + "Значением: " + news_item_description);
        newsPage.enterDescription(news_item_description);
        Allure.step("Сохраняем публикацию");
        newsPage.savePublication();
        Allure.step("Сверяем текст ошибки: " + R.string.error_saving);
        onView(withText(R.string.error_saving)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Категория'. Заполнено: табуляция") //"Поле категория заполнено: табуляция"
    @Test
    public void validationCategoryFieldNegativeTest6() {
        Allure.step("Переходим на страницу: Все новости");
        mainPage.goToAllNewsPage();
        Allure.step("Переходим на страницу: панель управления");
        newsPage.toEditNewsButton();
        Allure.step("Нажимаем на кнопку: создать публикацию");
        newsPage.toCreateNewsButton();
        Allure.step("Заполняем поле: категория; " + "Значением: табуляция");
        newsPage.enterCategory("    ");
        Allure.step("Заполняем поле: название; " + "Значением: " + news_item_title);
        newsPage.enterTitle(news_item_title);
        Allure.step("Заполняем поле дата значением: " + current_date);
        newsPage.enterDate(current_date);
        Allure.step("Заполняем поле время значением: " + current_time);
        newsPage.enterTime(current_time);
        Allure.step("Заполняем поле: описание; " + "Значением: " + news_item_description);
        newsPage.enterDescription(news_item_description);
        Allure.step("Сохраняем публикацию");
        newsPage.savePublication();
        Allure.step("Сверяем текст ошибки: " + R.string.empty_fields);
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Категория'. Заполнено: произвольный текст на латинице") //"Поле категория заполнено: произвольный текст на латинице"
    @Test
    public void validationCategoryFieldNegativeTest7() {
        Allure.step("Переходим на страницу: Все новости");
        mainPage.goToAllNewsPage();
        Allure.step("Переходим на страницу: панель управления");
        newsPage.toEditNewsButton();
        Allure.step("Нажимаем на кнопку: создать публикацию");
        newsPage.toCreateNewsButton();
        Allure.step("Заполняем поле: категория; " + "Значением: на латинице");
        newsPage.enterCategory("Category");
        Allure.step("Заполняем поле: название; " + "Значением: " + news_item_title);
        newsPage.enterTitle(news_item_title);
        Allure.step("Заполняем поле дата значением: " + current_date);
        newsPage.enterDate(current_date);
        Allure.step("Заполняем поле время значением: " + current_time);
        newsPage.enterTime(current_time);
        Allure.step("Заполняем поле: описание; " + "Значением: " + news_item_description);
        newsPage.enterDescription(news_item_description);
        Allure.step("Сохраняем публикацию");
        newsPage.savePublication();
        Allure.step("Сверяем текст ошибки: " + R.string.error_saving);
        onView(withText(R.string.error_saving)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Заголовок'. Заполнено: пусто") //"Поле заголовок заполнено: пусто"
    @Test
    public void validationTitleFieldNegativeTest1() {
        Allure.step("Переходим на страницу: Все новости");
        mainPage.goToAllNewsPage();
        Allure.step("Переходим на страницу: панель управления");
        newsPage.toEditNewsButton();
        Allure.step("Нажимаем на кнопку: создать публикацию");
        newsPage.toCreateNewsButton();
        Allure.step("Заполняем поле: категория; " + "Значением: " + news_item_category);
        newsPage.enterCategory(news_item_category);
        Allure.step("Заполняем поле: название; " + "Значением: пусто");
        newsPage.enterTitle("");
        Allure.step("Заполняем поле дата значением: " + current_date);
        newsPage.enterDate(current_date);
        Allure.step("Заполняем поле время значением: " + current_time);
        newsPage.enterTime(current_time);
        Allure.step("Заполняем поле: описание; " + "Значением: " + news_item_description);
        newsPage.enterDescription(news_item_description);
        Allure.step("Сохраняем публикацию");
        newsPage.savePublication();
        Allure.step("Сверяем текст ошибки: " + R.string.empty_fields);
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Заголовок'. Заполнено: пробел") //"Поле заголовок заполнено: пробел"
    @Test
    public void validationTitleFieldNegativeTest2() {
        Allure.step("Переходим на страницу: Все новости");
        mainPage.goToAllNewsPage();
        Allure.step("Переходим на страницу: панель управления");
        newsPage.toEditNewsButton();
        Allure.step("Нажимаем на кнопку: создать публикацию");
        newsPage.toCreateNewsButton();
        Allure.step("Заполняем поле: категория; " + "Значением: " + news_item_category);
        newsPage.enterCategory(news_item_category);
        Allure.step("Заполняем поле: название; " + "Значением: пробел");
        newsPage.enterTitle(" ");
        Allure.step("Заполняем поле дата значением: " + current_date);
        newsPage.enterDate(current_date);
        Allure.step("Заполняем поле время значением: " + current_time);
        newsPage.enterTime(current_time);
        Allure.step("Заполняем поле: описание; " + "Значением: " + news_item_description);
        newsPage.enterDescription(news_item_description);
        Allure.step("Сохраняем публикацию");
        newsPage.savePublication();
        Allure.step("Сверяем текст ошибки: " + R.string.empty_fields);
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Заголовок'. Заполнено: табуляция") //"Поле заголовок заполнено: табуляция"
    @Test
    public void validationTitleFieldNegativeTest3() {
        Allure.step("Переходим на страницу: Все новости");
        mainPage.goToAllNewsPage();
        Allure.step("Переходим на страницу: панель управления");
        newsPage.toEditNewsButton();
        Allure.step("Нажимаем на кнопку: создать публикацию");
        newsPage.toCreateNewsButton();
        Allure.step("Заполняем поле: категория; " + "Значением: " + news_item_category);
        newsPage.enterCategory(news_item_category);
        Allure.step("Заполняем поле: название; " + "Значением: табуляция");
        newsPage.enterTitle("    ");
        Allure.step("Заполняем поле дата значением: " + current_date);
        newsPage.enterDate(current_date);
        Allure.step("Заполняем поле время значением: " + current_time);
        newsPage.enterTime(current_time);
        Allure.step("Заполняем поле: описание; " + "Значением: " + news_item_description);
        newsPage.enterDescription(news_item_description);
        Allure.step("Сохраняем публикацию");
        newsPage.savePublication();
        Allure.step("Сверяем текст ошибки: " + R.string.empty_fields);
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Описание'. Заполнено: пусто") //"Поле описание заполнено: пусто"
    @Test
    public void validationDescriptionFieldNegativeTest1() {
        Allure.step("Переходим на страницу: Все новости");
        mainPage.goToAllNewsPage();
        Allure.step("Переходим на страницу: панель управления");
        newsPage.toEditNewsButton();
        Allure.step("Нажимаем на кнопку: создать публикацию");
        newsPage.toCreateNewsButton();
        Allure.step("Заполняем поле: категория; " + "Значением: " + news_item_category);
        newsPage.enterCategory(news_item_category);
        Allure.step("Заполняем поле: название; " + "Значением: " + news_item_title);
        newsPage.enterTitle(news_item_title);
        Allure.step("Заполняем поле дата значением: " + current_date);
        newsPage.enterDate(current_date);
        Allure.step("Заполняем поле время значением: " + current_time);
        newsPage.enterTime(current_time);
        Allure.step("Заполняем поле: описание; " + "Значением: пусто");
        newsPage.enterDescription("");
        Allure.step("Сохраняем публикацию");
        newsPage.savePublication();
        Allure.step("Сверяем текст ошибки: " + R.string.empty_fields);
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Описание'. Заполнено: пробел") //"Поле описание заполнено: пробел"
    @Test
    public void validationDescriptionFieldNegativeTest2() {
        Allure.step("Переходим на страницу: Все новости");
        mainPage.goToAllNewsPage();
        Allure.step("Переходим на страницу: панель управления");
        newsPage.toEditNewsButton();
        Allure.step("Нажимаем на кнопку: создать публикацию");
        newsPage.toCreateNewsButton();
        Allure.step("Заполняем поле: категория; " + "Значением: " + news_item_category);
        newsPage.enterCategory(news_item_category);
        Allure.step("Заполняем поле: название; " + "Значением: " + news_item_title);
        newsPage.enterTitle(news_item_title);
        Allure.step("Заполняем поле дата значением: " + current_date);
        newsPage.enterDate(current_date);
        Allure.step("Заполняем поле время значением: " + current_time);
        newsPage.enterTime(current_time);
        Allure.step("Заполняем поле: описание; " + "Значением: пробел");
        newsPage.enterDescription(" ");
        Allure.step("Сохраняем публикацию");
        newsPage.savePublication();
        Allure.step("Сверяем текст ошибки: " + R.string.empty_fields);
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Описание'. Заполнено: табуляция") //"Поле описание заполнено: табуляция"
    @Test
    public void validationDescriptionFieldNegativeTest3() {
        Allure.step("Переходим на страницу: Все новости");
        mainPage.goToAllNewsPage();
        Allure.step("Переходим на страницу: панель управления");
        newsPage.toEditNewsButton();
        Allure.step("Нажимаем на кнопку: создать публикацию");
        newsPage.toCreateNewsButton();
        Allure.step("Заполняем поле: категория; " + "Значением: " + news_item_category);
        newsPage.enterCategory(news_item_category);
        Allure.step("Заполняем поле: название; " + "Значением: " + news_item_title);
        newsPage.enterTitle(news_item_title);
        Allure.step("Заполняем поле дата значением: " + current_date);
        newsPage.enterDate(current_date);
        Allure.step("Заполняем поле время значением: " + current_time);
        newsPage.enterTime(current_time);
        Allure.step("Заполняем поле: описание; " + "Значением: табуляция");
        newsPage.enterDescription("    ");
        Allure.step("Сохраняем публикацию");
        newsPage.savePublication();
        Allure.step("Сверяем текст ошибки: " + R.string.empty_fields);
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Дата'. Заполнено: пусто") //"Поле дата заполнено: пусто"
    @Test
    public void validationDateFieldNegativeTest1() {
        Allure.step("Переходим на страницу: Все новости");
        mainPage.goToAllNewsPage();
        Allure.step("Переходим на страницу: панель управления");
        newsPage.toEditNewsButton();
        Allure.step("Нажимаем на кнопку: создать публикацию");
        newsPage.toCreateNewsButton();
        Allure.step("Заполняем поле: категория; " + "Значением: " + news_item_category);
        newsPage.enterCategory(news_item_category);
        Allure.step("Заполняем поле: название; " + "Значением: " + news_item_title);
        newsPage.enterTitle(news_item_title);
        Allure.step("Заполняем поле дата значением: пусто");
        newsPage.enterDate("");
        Allure.step("Заполняем поле время значением: " + current_time);
        newsPage.enterTime(current_time);
        Allure.step("Заполняем поле: описание; " + "Значением: " + news_item_description);
        newsPage.enterDescription(news_item_description);
        Allure.step("Сохраняем публикацию");
        newsPage.savePublication();
        Allure.step("Сверяем текст ошибки: " + R.string.empty_fields);
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Время'. Заполнено: пусто")  //"Поле время заполнено: пусто"
    @Test
    public void validationTimeFieldNegativeTest1() {
        Allure.step("Переходим на страницу: Все новости");
        mainPage.goToAllNewsPage();
        Allure.step("Переходим на страницу: панель управления");
        newsPage.toEditNewsButton();
        Allure.step("Нажимаем на кнопку: создать публикацию");
        newsPage.toCreateNewsButton();
        Allure.step("Заполняем поле: категория; " + "Значением: " + news_item_category);
        newsPage.enterCategory(news_item_category);
        Allure.step("Заполняем поле: название; " + "Значением: " + news_item_title);
        newsPage.enterTitle(news_item_title);
        Allure.step("Заполняем поле дата значением: " + current_date);
        newsPage.enterDate(current_date);
        Allure.step("Заполняем поле время значением: пусто");
        newsPage.enterTime("");
        Allure.step("Заполняем поле: описание; " + "Значением: " + news_item_description);
        newsPage.enterDescription(news_item_description);
        Allure.step("Сохраняем публикацию");
        newsPage.savePublication();
        Allure.step("Сверяем текст ошибки: " + R.string.empty_fields);
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

}
