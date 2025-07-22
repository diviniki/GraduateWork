package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import static ru.iteco.fmhandroid.ui.pages.BaseFunctions.checkObjectDisplayed;
import static ru.iteco.fmhandroid.ui.pages.BaseFunctions.checkTextInField;
import static ru.iteco.fmhandroid.ui.pages.BaseFunctions.clickByButton;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AboutPage {
    private static int about_version_title_text_view = R.id.about_version_title_text_view;
    private static int about_version_value_text_view = R.id.about_version_value_text_view;
    private static String string_about_version_value_text_view = "1.0.0";
    private static int about_back_image_button = R.id.about_back_image_button;
    private static int about_company_info_label_text_view = R.id.about_company_info_label_text_view;
    private static String string_about_company_info_label_text_view = "© Айтеко, 2022";

    // "Ищем 'Версия' на странице"
    public void checkVersion() {
        ViewInteraction versionPage = onView(withId(about_version_title_text_view));
        checkObjectDisplayed(versionPage);
    }

    // Ищем значение версии
    public void checkVersionValue() {
        ViewInteraction versionValue = onView(withId(about_version_value_text_view));
        checkObjectDisplayed(versionValue);
        checkTextInField(versionValue, string_about_version_value_text_view);
    }

    // "Ищем торговый знак на странице"
    public void checkAboutCompany() {
        ViewInteraction aboutCompany = onView(withId(about_company_info_label_text_view));
        checkObjectDisplayed(aboutCompany);
        checkTextInField(aboutCompany, string_about_company_info_label_text_view);
    }

    // "Вернуться на главную"
    public void getBackToMainPage() {
        ViewInteraction backButton = onView(withId(about_back_image_button));
        checkObjectDisplayed(backButton);
        clickByButton(backButton);
    }

}
