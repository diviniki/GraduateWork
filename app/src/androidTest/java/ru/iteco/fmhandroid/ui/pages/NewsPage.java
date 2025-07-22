package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.CurrentDate.*;
import static ru.iteco.fmhandroid.ui.pages.BaseFunctions.checkActivityStatusInRecyclerViewActionsWithDescendant;
import static ru.iteco.fmhandroid.ui.pages.BaseFunctions.checkObjectDisplayed;
import static ru.iteco.fmhandroid.ui.pages.BaseFunctions.checkTextInField;
import static ru.iteco.fmhandroid.ui.pages.BaseFunctions.clickByButton;
import static ru.iteco.fmhandroid.ui.pages.BaseFunctions.clickByRecyclerViewActionsNoWithDescendant;
import static ru.iteco.fmhandroid.ui.pages.BaseFunctions.clickByRecyclerViewActionsWithDescendant;
import static ru.iteco.fmhandroid.ui.pages.BaseFunctions.clickByRecyclerViewActionsWithDescendantAndDelete;
import static ru.iteco.fmhandroid.ui.pages.BaseFunctions.enterTextIntoTheFiled;
import static ru.iteco.fmhandroid.ui.pages.BaseFunctions.scrollToAndClickByButton;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.core.IsInstanceOf;

import ru.iteco.fmhandroid.CurrentDate;
import ru.iteco.fmhandroid.DataValues;
import ru.iteco.fmhandroid.R;

public class NewsPage {
    private static int edit_news_material_button = R.id.edit_news_material_button;
    private static int sort_news_material_button = R.id.sort_news_material_button;
    private static int filter_news_material_button = R.id.filter_news_material_button;
    private static int add_news_image_view = R.id.add_news_image_view;
    private static int news_item_category_text_auto_complete_text_view = R.id.news_item_category_text_auto_complete_text_view;
    static String hint_news_item_category_text_auto_complete_text_view = "Категория";
    private static int news_item_title_text_input_edit_text = R.id.news_item_title_text_input_edit_text;
    static String hint_news_item_title_text_input_edit_text = "Заголовок";
    private static int news_item_publish_date_text_input_edit_text = R.id.news_item_publish_date_text_input_edit_text;
    static String hint_news_item_publish_date_text_input_edit_text = "Дата публикации";
    private static int news_item_publish_time_text_input_edit_text = R.id.news_item_publish_time_text_input_edit_text;
    static String hint_news_item_publish_time_text_input_edit_text = "Время";
    private static int news_item_description_text_input_edit_text = R.id.news_item_description_text_input_edit_text;
    static String hint_news_item_description_text_input_edit_text = "Описание";
    private static int save_button = R.id.save_button;
    private static int delete_news_item_image_view = R.id.delete_news_item_image_view;
    private static int button1 = android.R.id.button1; // Кнопка "ОК"
    private static int edit_news_item_image_view = R.id.edit_news_item_image_view;
    private static int switcher = R.id.switcher;



    // "Ищем кнопку 'Панель управления'"
    public void editNewsButton() {
        ViewInteraction editNewsButton = onView(withId(edit_news_material_button));
        checkObjectDisplayed(editNewsButton);
    }

    // "Открываем 'Панель управления'"
    public void toEditNewsButton() {
        ViewInteraction toEditNewsButton = onView(withId(edit_news_material_button));
        clickByButton(toEditNewsButton);
    }

    // "Ищем кнопку 'Соритровать новость'"
    public void sortNewsButton() {
        ViewInteraction sortNewsButton = onView(withId(sort_news_material_button));
        checkObjectDisplayed(sortNewsButton);
    }

    // "Сортировать"
    public void toSortNewsButton() {
        ViewInteraction toSortNewsButton = onView(withId(sort_news_material_button));
        checkObjectDisplayed(toSortNewsButton);
        clickByButton(toSortNewsButton);
    }

    // "Ищем кнопку 'Фильтровать новость'"
    public void filterNewsButton() {
        ViewInteraction filterNewsButton = onView(withId(filter_news_material_button));
        checkObjectDisplayed(filterNewsButton);
    }

    // "Фильтровать"
    public void toFilterNewsButton() {
        ViewInteraction toFilterNewsButton = onView(withId(filter_news_material_button));
        checkObjectDisplayed(toFilterNewsButton);
        clickByButton(toFilterNewsButton);
    }

    // "Создать"
    public void toCreateNewsButton() {
        ViewInteraction toCreateNewsButton = onView(withId(add_news_image_view));
        checkObjectDisplayed(toCreateNewsButton);
        clickByButton(toCreateNewsButton);
    }

    // "Ищем и заполняем поле Категория"
    public void enterCategory(String category) {
        ViewInteraction textInputCategory = onView(allOf(withHint(hint_news_item_category_text_auto_complete_text_view), withId(news_item_category_text_auto_complete_text_view)));
        checkObjectDisplayed(textInputCategory);
        enterTextIntoTheFiled(textInputCategory, category);
    }

    // "Ищем и заполняем поле Заголовок"
    public void enterTitle(String title) {
        ViewInteraction textInputTitle = onView(allOf(withHint(hint_news_item_title_text_input_edit_text), withId(news_item_title_text_input_edit_text)));
        checkObjectDisplayed(textInputTitle);
        enterTextIntoTheFiled(textInputTitle, title);
    }

    // "Ищем и заполняем поле Дата"
    public void enterDate(String date) {
        ViewInteraction textInputDate = onView(allOf(withHint(hint_news_item_publish_date_text_input_edit_text), withId(news_item_publish_date_text_input_edit_text)));
        checkObjectDisplayed(textInputDate);
        enterTextIntoTheFiled(textInputDate, date);
    }

    // "Ищем и заполняем поле Время"
    public void enterTime(String time) {
        ViewInteraction textInputTime = onView(allOf(withHint(hint_news_item_publish_time_text_input_edit_text), withId(news_item_publish_time_text_input_edit_text)));
        checkObjectDisplayed(textInputTime);
        enterTextIntoTheFiled(textInputTime, time);
    }

    // "Ищем и заполняем поле Описание"
    public void enterDescription(String description) {
        ViewInteraction textInputDescription = onView(allOf(withHint(hint_news_item_description_text_input_edit_text), withId(news_item_description_text_input_edit_text)));
        checkObjectDisplayed(textInputDescription);
        enterTextIntoTheFiled(textInputDescription, description);
    }

    // Сохранить публикацию
    public void savePublication() {
        ViewInteraction saveButton = onView(withId(save_button));
        checkObjectDisplayed(saveButton);
        clickByButton(saveButton);
    }

    // Поиск публикации по содержимому
    public void searchPublication(String search_text) {
        ViewInteraction recyclerView = onView(withId(R.id.news_list_recycler_view));
        clickByRecyclerViewActionsWithDescendant(recyclerView, search_text);
    }

    // Удалить публикацию
    public void deletePublication(String search_text) {
        ViewInteraction recyclerView = onView(withId(R.id.news_list_recycler_view));
        clickByRecyclerViewActionsWithDescendantAndDelete(recyclerView, search_text, delete_news_item_image_view);
        ViewInteraction confirmButton = onView(withId(button1));
        confirmButton.inRoot(isDialog());
        checkObjectDisplayed(confirmButton);
        scrollToAndClickByButton(confirmButton);
    }

    // Убедиться в отсутствии публикации
    public void searchDeletedPublication(String search_text) {
        ViewInteraction recyclerView = onView(withId(R.id.news_list_recycler_view));
        clickByRecyclerViewActionsNoWithDescendant(recyclerView, search_text);
    }

    // Проверка статуса активности у конкретной публикации
    public void checkActivityStatus(String search_text, String search_status) {
        ViewInteraction recyclerView = onView(withId(R.id.news_list_recycler_view));
        checkActivityStatusInRecyclerViewActionsWithDescendant(recyclerView, search_text, search_status);
    }

    // Редактировать публикацию
    public void editPublication(String search_text) {
        ViewInteraction recyclerView = onView(withId(R.id.news_list_recycler_view));
        clickByRecyclerViewActionsWithDescendantAndDelete(recyclerView, search_text, edit_news_item_image_view);
    }

    // "Переключение ползунка активности "
    public void toSwitchActivityButton() {
        ViewInteraction switchActivityButton = onView(withId(switcher));
        checkObjectDisplayed(switchActivityButton);
        scrollToAndClickByButton(switchActivityButton);
    }


}
