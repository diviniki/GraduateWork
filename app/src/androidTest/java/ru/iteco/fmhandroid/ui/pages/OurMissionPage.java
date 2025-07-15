package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.pages.BaseFunctions.checkObjectDisplayed;
import static ru.iteco.fmhandroid.ui.pages.BaseFunctions.checkTextInField;
import static ru.iteco.fmhandroid.ui.pages.BaseFunctions.clickByButton;
import static ru.iteco.fmhandroid.ui.pages.MainPage.clickByMenu;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class OurMissionPage {
    private static int our_mission_title_text_view = R.id.our_mission_title_text_view;
    static String string_our_mission_title_text_view = "Главное - жить любя";
    static String section_main = "Главная";

    // Проверяем титул страницы
    public static void checkTitle() {
        ViewInteraction titleText = onView(withId(our_mission_title_text_view));
        checkObjectDisplayed(titleText);
        checkTextInField(titleText, string_our_mission_title_text_view);
    }

    // Вернуться на главную
    public static void goBackToMainPage() {
        clickByMenu();
        ViewInteraction mainPageButton = onView(allOf(withId(android.R.id.title), withText(section_main)));
        checkObjectDisplayed(mainPageButton);
        clickByButton(mainPageButton);
    }
}
