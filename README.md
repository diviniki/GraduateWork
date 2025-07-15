# Приложение «Мобильный хоспис». Процедура запуска авто-тестов

## Для запуска всех тест-сьютов:
В консоли терминала выполнить команду: *./gradlew connectedAndroidTest*

## Для запуска тест-сьютов по отдельности:
В консоли терминала выполнить команду:
1. Для запуска тестирования страницы авторизации (позитивные сценарии): *./gradlew app:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=ru.iteco.fmhandroid.ui.package.AuthPagePositiveTest*
2. Для запуска тестирования страницы авторизации (негативные сценарии): *./gradlew app:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=ru.iteco.fmhandroid.ui.package.AuthPageNegativeTest*
3. Для запуска тестирования главной страницы: *./gradlew app:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=ru.iteco.fmhandroid.ui.package.MainPageTest*
4. Для запуска тестирования страницы новостей: *./gradlew app:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=ru.iteco.fmhandroid.ui.package.NewsPageTest*
5. Для запуска тестирования страницы "О приложении": *./gradlew app:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=ru.iteco.fmhandroid.ui.package.AboutPageTest*
6. Для запуска тестирования страницы "Наша миссия": *./gradlew app:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=ru.iteco.fmhandroid.ui.package.OurMissionPageTest*
7. Для запуска тестирования формы создания новости (негативные сценарии): *./gradlew app:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=ru.iteco.fmhandroid.ui.package.ValidationNewsFormTest*