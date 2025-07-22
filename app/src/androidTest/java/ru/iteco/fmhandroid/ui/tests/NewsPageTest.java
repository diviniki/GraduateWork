package ru.iteco.fmhandroid.ui.tests;


import static ru.iteco.fmhandroid.DataValues.current_date;
import static ru.iteco.fmhandroid.DataValues.current_time;
import static ru.iteco.fmhandroid.DataValues.new_publication_status;
import static ru.iteco.fmhandroid.DataValues.news_item_category;
import static ru.iteco.fmhandroid.DataValues.news_item_description;
import static ru.iteco.fmhandroid.DataValues.news_item_title;

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
import ru.iteco.fmhandroid.ui.pages.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsPageTest {
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
        Allure.step("Переходим на страницу: Все новости");
        mainPage.goToAllNewsPage();
        Allure.step("Проверяем, что страница открылась");
        newsPage.editNewsButton();
    }

    @Description("Проверка отображения кнопки сортировки") //"Проверяем наличие кнопки сортировки"
    @Test
    public void checkSortNewsButtonTest() {
        Allure.step("Переходим на страницу: Все новости");
        mainPage.goToAllNewsPage();
        Allure.step("Проверяем наличие кнопки сортировки");
        newsPage.sortNewsButton();
    }

    @Description("Проверка отображения кнопки фильтра") //"Проверяем наличие кнопки фильтровать"
    @Test
    public void checkFilterNewsButtonTest() {
        Allure.step("Переходим на страницу: Все новости");
        mainPage.goToAllNewsPage();
        Allure.step("Проверяем наличие кнопки фильтрации");
        newsPage.filterNewsButton();
    }

    @Description("Создание новой публикации") //"Создаем публикацию"
    @Test
    public void toCreateNewsTest() {
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
        Allure.step("Вводим описание: " + news_item_description);
        newsPage.enterDescription(news_item_description);
        Allure.step("Сохраняем публикацию");
        newsPage.savePublication();
        Allure.step("Убеждаемся, что публикация появилась в общем списке");
        newsPage.searchPublication(news_item_title);
        Allure.step("Удаляем публикацию");
        newsPage.deletePublication(news_item_title);
    }

    @Description("Редактирование публикации. Переключение статус с Активна на Неактивна.") //"Редктировать публикацию (Переключаем статус на 'Неактивна')"
    @Test
    public void toEditNewsTest() {
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
        Allure.step("Вводим описание: " + news_item_description);
        newsPage.enterDescription(news_item_description);
        Allure.step("Сохраняем публикацию");
        newsPage.savePublication();
        Allure.step("Нажимаем кнопку редактировать: по созданной публикации");
        newsPage.editPublication(news_item_title);
        Allure.step("Сдвигаем ползунок в статус: Не авктивна");
        newsPage.toSwitchActivityButton();
        Allure.step("Сохраняем публикацию");
        newsPage.savePublication();
        Allure.step("Убеждаемся, что публикация находится в статусе: Не авктивна");
        newsPage.checkActivityStatus(news_item_title, new_publication_status);
        //Allure.step("Удаляем публикацию");
        //newsPage.deletePublication(news_item_title);
    }

    @Description("Удаление публикации") //"Удаляем публикацию"
    @Test
    public void toDeleteNewsTest() {
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
        Allure.step("Вводим описание: " + news_item_description);
        newsPage.enterDescription(news_item_description);
        Allure.step("Сохраняем публикацию");
        newsPage.savePublication();
        Allure.step("Удаляем публикацию");
        newsPage.deletePublication(news_item_title);
        Allure.step("Убеждаемся, что созданная публикация отсутствует в общем списке");
        newsPage.searchDeletedPublication(news_item_title);
    }
}
