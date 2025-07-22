package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import static ru.iteco.fmhandroid.ui.pages.BaseFunctions.checkObjectDisplayed;
import static ru.iteco.fmhandroid.ui.pages.BaseFunctions.checkObjectNotDisplayed;
import static ru.iteco.fmhandroid.ui.pages.BaseFunctions.clickByButton;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class MainPage {
    private static int main_menu_image_button = R.id.main_menu_image_button;
    private static int trademark_image_view = R.id.trademark_image_view;
    private static int authorization_image_button = R.id.authorization_image_button;
    private static int our_mission_image_button = R.id.our_mission_image_button;
    private static int all_news_text_view = R.id.all_news_text_view;
    private static int expand_material_button = R.id.expand_material_button;
    static String section_news = "Новости";
    static String section_about = "О приложении";
    static String section_exit = "Выйти";

    // "Ищем logo страницы"
    public void checkLogo() {
        ViewInteraction logoPage = onView(withId(trademark_image_view));
        checkObjectDisplayed(logoPage);
    }

    // "Клик на кнопку меню"
    public static void clickByMenu() {
        ViewInteraction menuButton = onView(withId(main_menu_image_button));
        checkObjectDisplayed(menuButton);
        clickByButton(menuButton);
    }

    // "Переход на страницу 'О приложении'"
    public void goToAboutPage() {
        clickByMenu();
        ViewInteraction aboutPageButton = onView(allOf(withId(android.R.id.title), withText(section_about)));
        checkObjectDisplayed(aboutPageButton);
        clickByButton(aboutPageButton);

    }

    // "Переход на страницу 'Новости' через меню"
    public void goToNewsPage() {
        clickByMenu();
        ViewInteraction newsPageButton = onView(allOf(withId(android.R.id.title), withText(section_news)));
        checkObjectDisplayed(newsPageButton);
        clickByButton(newsPageButton);
    }

    // "Переход на страницу 'Наша миссия'"
    public void goToMissionPage() {
        ViewInteraction missionPageButton = onView(withId(our_mission_image_button));
        checkObjectDisplayed(missionPageButton);
        clickByButton(missionPageButton);
    }

    // "Переход на страницу 'Новости' через кнопку 'Все новости'"
    public void goToAllNewsPage() {
        ViewInteraction allNewsPageButton = onView(withId(all_news_text_view));
        checkObjectDisplayed(allNewsPageButton);
        clickByButton(allNewsPageButton);
    }

    // "Свернуть раздел новостей"
    public void expandMaterial() {
        ViewInteraction expandMaterialButton = onView(withId(expand_material_button));
        ViewInteraction allNewsPageButton = onView(withId(all_news_text_view));
        checkObjectDisplayed(expandMaterialButton);
        clickByButton(expandMaterialButton);
        checkObjectNotDisplayed(allNewsPageButton);
    }

    // "Выход из профиля"
    public void toLogOut() {
        ViewInteraction authButton = onView(withId(authorization_image_button));
        checkObjectDisplayed(authButton);
        clickByButton(authButton);
        ViewInteraction exitButton = onView(allOf(withId(android.R.id.title), withText(section_exit)));
        checkObjectDisplayed(exitButton);
        clickByButton(exitButton);
    }
}
