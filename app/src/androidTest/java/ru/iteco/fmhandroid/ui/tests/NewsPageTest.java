package ru.iteco.fmhandroid.ui.tests;


import static ru.iteco.fmhandroid.DataValues.current_date;
import static ru.iteco.fmhandroid.DataValues.current_time;
import static ru.iteco.fmhandroid.DataValues.new_publication_status;
import static ru.iteco.fmhandroid.DataValues.news_item_category;
import static ru.iteco.fmhandroid.DataValues.news_item_description;
import static ru.iteco.fmhandroid.DataValues.news_item_title;
import static ru.iteco.fmhandroid.ui.pages.AuthPage.checkLoadPage;
import static ru.iteco.fmhandroid.ui.pages.AuthPage.toLogIn;
import static ru.iteco.fmhandroid.ui.pages.MainPage.goToAllNewsPage;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.checkActivityStatus;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.deletePublication;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.editNewsButton;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.editPublication;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.enterCategory;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.enterDate;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.enterDescription;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.enterTime;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.enterTitle;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.filterNewsButton;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.savePublication;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.searchDeletedPublication;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.searchPublication;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.sortNewsButton;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.toCreateNewsButton;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.toEditNewsButton;
import static ru.iteco.fmhandroid.ui.pages.NewsPage.toSwitchActivityButton;

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
public class NewsPageTest {
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


    @Description("Проверка отображения страницы через наличие кнопки открытия Панели управления") //"Проверяем, что страница загружается"
    @Test
    public void checkEditNewsButtonTest() {
        goToAllNewsPage();
        editNewsButton();
    }

    @Description("Проверка отображения кнопки сортировки") //"Проверяем наличие кнопки сортировки"
    @Test
    public void checkSortNewsButtonTest() {
        goToAllNewsPage();
        sortNewsButton();
    }

    @Description("Проверка отображения кнопки фильтра") //"Проверяем наличие кнопки фильтровать"
    @Test
    public void checkFilterNewsButtonTest() {
        goToAllNewsPage();
        filterNewsButton();
    }

    @Description("Создание новой публикации") //"Создаем публикацию"
    @Test
    public void toCreateNewsTest() {
        goToAllNewsPage();
        toEditNewsButton();
        toCreateNewsButton();
        enterCategory(news_item_category);
        enterTitle(news_item_title);
        enterDate(current_date);
        enterTime(current_time);
        enterDescription(news_item_description);
        savePublication();
        searchPublication(news_item_title);
        deletePublication(news_item_title);
    }

    @Description("Редактирование публикации. Переключение статус с Активна на Неактивна.") //"Редктировать публикацию (Переключаем статус на 'Неактивна')"
    @Test
    public void toEditNewsTest() {
        goToAllNewsPage();
        toEditNewsButton();
        toCreateNewsButton();
        enterCategory(news_item_category);
        enterTitle(news_item_title);
        enterDate(current_date);
        enterTime(current_time);
        enterDescription(news_item_description);
        savePublication();
        editPublication(news_item_title);
        toSwitchActivityButton();
        savePublication();
        checkActivityStatus(news_item_title, new_publication_status);
        deletePublication(news_item_title);
    }

    @Description("Удаление публикации") //"Удаляем публикацию"
    @Test
    public void toDeleteNewsTest() {
        goToAllNewsPage();
        toEditNewsButton();
        toCreateNewsButton();
        enterCategory(news_item_category);
        enterTitle(news_item_title);
        enterDate(current_date);
        enterTime(current_time);
        enterDescription(news_item_description);
        savePublication();
        deletePublication(news_item_title);
        searchDeletedPublication(news_item_title);
    }
}
