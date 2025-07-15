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
import static ru.iteco.fmhandroid.ui.pages.AuthPage.checkLoadPage;
import static ru.iteco.fmhandroid.ui.pages.AuthPage.toLogIn;
import static ru.iteco.fmhandroid.ui.pages.MainPage.goToAllNewsPage;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.deletePublication;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.enterCategory;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.enterDate;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.enterDescription;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.enterTime;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.enterTitle;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.savePublication;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.searchPublication;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.toCreateNewsButton;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.toEditNewsButton;

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
import io.qameta.allure.kotlin.Description;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class ValidationNewsFormTest {
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
        goToAllNewsPage();
        toEditNewsButton();
        toCreateNewsButton();
        enterCategory("");
        enterTitle(news_item_title);
        enterDate(current_date);
        enterTime(current_time);
        enterDescription(news_item_description);
        savePublication();
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Категория'. Заполнено: пробел") //"Поле категория заполнено: пробел"
    @Test
    public void validationCategoryFieldNegativeTest2() {
        goToAllNewsPage();
        toEditNewsButton();
        toCreateNewsButton();
        enterCategory(" ");
        enterTitle(news_item_title);
        enterDate(current_date);
        enterTime(current_time);
        enterDescription(news_item_description);
        savePublication();
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Категория'. Заполнено: в верхнем регистре") //"Поле категория заполнено: в верхнем регистре"
    @Test
    public void validationCategoryFieldNegativeTest3() {
        goToAllNewsPage();
        toEditNewsButton();
        toCreateNewsButton();
        enterCategory(news_item_category.toUpperCase());
        enterTitle(news_item_title);
        enterDate(current_date);
        enterTime(current_time);
        enterDescription(news_item_description);
        savePublication();
        onView(withText(R.string.error_saving)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Категория'. Заполнено: произвольный текст на кириллице") //"Поле категория заполнено: произвольный текст"
    @Test
    public void validationCategoryFieldNegativeTest4() {
        goToAllNewsPage();
        toEditNewsButton();
        toCreateNewsButton();
        enterCategory("МояКатегория");
        enterTitle(news_item_title);
        enterDate(current_date);
        enterTime(current_time);
        enterDescription(news_item_description);
        savePublication();
        onView(withText(R.string.error_saving)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Категория'. Заполнено: со спец.символами") //"Поле категория заполнено: со спец.символами"
    @Test
    public void validationCategoryFieldNegativeTest5() {
        goToAllNewsPage();
        toEditNewsButton();
        toCreateNewsButton();
        enterCategory(news_item_category + "!");
        enterTitle(news_item_title);
        enterDate(current_date);
        enterTime(current_time);
        enterDescription(news_item_description);
        savePublication();
        onView(withText(R.string.error_saving)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Категория'. Заполнено: табуляция") //"Поле категория заполнено: табуляция"
    @Test
    public void validationCategoryFieldNegativeTest6() {
        goToAllNewsPage();
        toEditNewsButton();
        toCreateNewsButton();
        enterCategory("    ");
        enterTitle(news_item_title);
        enterDate(current_date);
        enterTime(current_time);
        enterDescription(news_item_description);
        savePublication();
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Категория'. Заполнено: произвольный текст на латинице") //"Поле категория заполнено: произвольный текст на латинице"
    @Test
    public void validationCategoryFieldNegativeTest7() {
        goToAllNewsPage();
        toEditNewsButton();
        toCreateNewsButton();
        enterCategory("Category");
        enterTitle(news_item_title);
        enterDate(current_date);
        enterTime(current_time);
        enterDescription(news_item_description);
        savePublication();
        onView(withText(R.string.error_saving)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Заголовок'. Заполнено: пусто") //"Поле заголовок заполнено: пусто"
    @Test
    public void validationTitleFieldNegativeTest1() {
        goToAllNewsPage();
        toEditNewsButton();
        toCreateNewsButton();
        enterCategory(news_item_category);
        enterTitle("");
        enterDate(current_date);
        enterTime(current_time);
        enterDescription(news_item_description);
        savePublication();
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Заголовок'. Заполнено: пробел") //"Поле заголовок заполнено: пробел"
    @Test
    public void validationTitleFieldNegativeTest2() {
        goToAllNewsPage();
        toEditNewsButton();
        toCreateNewsButton();
        enterCategory(news_item_category);
        enterTitle(" ");
        enterDate(current_date);
        enterTime(current_time);
        enterDescription(news_item_description);
        savePublication();
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Заголовок'. Заполнено: табуляция") //"Поле заголовок заполнено: табуляция"
    @Test
    public void validationTitleFieldNegativeTest3() {
        goToAllNewsPage();
        toEditNewsButton();
        toCreateNewsButton();
        enterCategory(news_item_category);
        enterTitle("    ");
        enterDate(current_date);
        enterTime(current_time);
        enterDescription(news_item_description);
        savePublication();
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Описание'. Заполнено: пусто") //"Поле описание заполнено: пусто"
    @Test
    public void validationDescriptionFieldNegativeTest1() {
        goToAllNewsPage();
        toEditNewsButton();
        toCreateNewsButton();
        enterCategory(news_item_category);
        enterTitle(news_item_title);
        enterDate(current_date);
        enterTime(current_time);
        enterDescription("");
        savePublication();
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Описание'. Заполнено: пробел") //"Поле описание заполнено: пробел"
    @Test
    public void validationDescriptionFieldNegativeTest2() {
        goToAllNewsPage();
        toEditNewsButton();
        toCreateNewsButton();
        enterCategory(news_item_category);
        enterTitle(news_item_title);
        enterDate(current_date);
        enterTime(current_time);
        enterDescription(" ");
        savePublication();
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Описание'. Заполнено: табуляция") //"Поле описание заполнено: табуляция"
    @Test
    public void validationDescriptionFieldNegativeTest3() {
        goToAllNewsPage();
        toEditNewsButton();
        toCreateNewsButton();
        enterCategory(news_item_category);
        enterTitle(news_item_title);
        enterDate(current_date);
        enterTime(current_time);
        enterDescription("    ");
        savePublication();
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Дата'. Заполнено: пусто") //"Поле дата заполнено: пусто"
    @Test
    public void validationDateFieldNegativeTest1() {
        goToAllNewsPage();
        toEditNewsButton();
        toCreateNewsButton();
        enterCategory(news_item_category);
        enterTitle(news_item_title);
        enterDate("");
        enterTime(current_time);
        enterDescription(news_item_description);
        savePublication();
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Description("Валидация поля 'Время'. Заполнено: пусто")  //"Поле время заполнено: пусто"
    @Test
    public void validationTimeFieldNegativeTest1() {
        goToAllNewsPage();
        toEditNewsButton();
        toCreateNewsButton();
        enterCategory(news_item_category);
        enterTitle(news_item_title);
        enterDate(current_date);
        enterTime("");
        enterDescription(news_item_description);
        savePublication();
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

}
