package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import android.view.View;

import org.hamcrest.Matcher;

import io.qameta.allure.kotlin.Step;

public class BaseFunctions {
    @Step("Проверяем отображение искомого объекта на странице")
    public static void checkObjectDisplayed(ViewInteraction displayedObject) {
        try {
            displayedObject.check(matches(isDisplayed()));
        }
        catch (NoMatchingViewException e) {
            System.out.println("Искомый объект на странице не найден");
        }
    }

    @Step("Проверяем отсутствие искомого объекта на странице")
    public static void checkObjectNotDisplayed(ViewInteraction notDisplayedObject) {
        try {
            notDisplayedObject.check(matches(not(isDisplayed())));
        }
        catch (Exception e) {
            System.out.println("Что-то пошло не так; Объект найден");
        }
    }

    @Step("Клик по кнопке")
    public static void clickByButton(ViewInteraction buttonObject) {
        buttonObject.perform(click());
    }

    @Step("Скрол и Клик по кнопке")
    public static void scrollToAndClickByButton(ViewInteraction buttonObject) {
        buttonObject.perform(scrollTo(), click());
    }

    @Step("Поиск в списке по содержимому")
    public static void clickByRecyclerViewActionsWithDescendant(ViewInteraction recyclerObject, String search_text) {
        recyclerObject.perform(RecyclerViewActions.actionOnItem(hasDescendant(withText(search_text)), click()));
    }

    @Step("Проверить, что элемент с таким содержимым отсутствует в списке")
    public static void clickByRecyclerViewActionsNoWithDescendant(ViewInteraction recyclerObject, String search_text) {
        recyclerObject.check(matches(not(hasDescendant(withText(search_text)))));
    }

    @Step("Проверить статус активности у конкретной публикации")
    public static void checkActivityStatusInRecyclerViewActionsWithDescendant(ViewInteraction recyclerObject, String search_text, String search_status) {
        recyclerObject.check(matches(allOf(hasDescendant(withText(search_text)),hasDescendant(withText(search_status)))));
    }

    @Step("Поиск в списке по содержимому, затем удаление")
    public static void clickByRecyclerViewActionsWithDescendantAndDelete(ViewInteraction recyclerObject, String search_text, int id_object) {
        recyclerObject.perform(RecyclerViewActions.actionOnItem(hasDescendant(withText(search_text)), clickOnChildViewWithId(id_object)));
    }

    @Step("Проверка содержимого в поле текста")
    public static void checkTextInField(ViewInteraction inField, String lookingForThisText) {
        try {
            inField.check(matches(withText(lookingForThisText)));
        }
        catch (Exception e) {
            System.out.println("Поле не содержит передаваемый текст");
        }
    }

    @Step("Заполняем поле текстом")
    public static void enterTextIntoTheFiled(ViewInteraction inField, String enterThisText) {
            inField.perform(replaceText(enterThisText), closeSoftKeyboard());
    }

    // Вспомогательная функция
    public static ViewAction clickOnChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }


}
