# Проект автоматизации тестирования API на платформе [regres.in](https://reqres.in/)

##	Содержание

- [Технологии и инструменты](#technologist-технологии-и-инструменты)
- [Реализованныe проверки](#bookmark_tabs-реализованные-проверки)
- [Запуск тестов из терминала](#computer-запуск-тестов-из-терминала)
- [Запуск тестов в Jenkins](#-запуск-тестов-в-jenkins)
- [Отчет о результатах тестирования в Allure Report](#-отчет-о-результатах-тестирования-в-Allure-report)
- [Интеграция с Allure TestOps](#-интеграция-с-allure-testops)
- [Уведомления в Telegram с использованием бота](#-уведомления-в-telegram-с-использованием-бота)


## Технологии и инструменты
<p  align="center">

<img width="5%" title="IntelliJ IDEA" src="src/test/resources/img/logo/Intelij_IDEA.svg">
<img width="5%" title="Java" src="src/test/resources/img/logo/Java.svg">
<img width="5%" title="Gradle" src="src/test/resources/img/logo/Gradle.svg">
<img width="5%" title="Junit5" src="src/test/resources/img/logo/JUnit5.svg">
<img width="5%" title="Allure Report" src="src/test/resources/img/logo/Allure_Report.svg">
<img width="5%" title="Allure TestOps" src="src/test/resources/img/logo/AllureTestOps.svg">
<img width="5%" title="GitHub" src="src/test/resources/img/logo/GitHub.svg">
<img width="5%" title="Jenkins" src="src/test/resources/img/logo/Jenkins.svg">
<img width="5%" title="Telegram" src="src/test/resources/img/logo/Telegram.svg">

</p>

## Реализованные проверки:

- Создание пользователя
- Удаление пользователя
- Обновление данных пользователя
- Проверка пользователя по id
- Успешная авторизация

## :computer: Запуск тестов из терминала

### Удаленный запуск тестов

```bash
gradle clean test 
```

## <img width="4%" title="Jenkins" src="src/test/resources/img/logo/Jenkins.svg"> Запуск тестов в [Jenkins](https://jenkins.autotests.cloud/job/regresInAPI_tests/)

Для запуска сборки необходимо нажать кнопку <code><strong>*Собрать сейчас*</strong></code>.

<p align="center">
  <img src="src/test/resources/img/screenshots/Jenkins.png" alt="Jenkins" width="800">
</p>

После выполнения сборки, в блоке <code><strong>*История сборок*</strong></code> напротив номера сборки отобразятся
иконки *Allure Report* и *Allure TestOPS*, которые по клику открывают страницы соответствующих отчетов.

<p align="center">
  <img src="src/test/resources/img/screenshots/Allure.png">
</p>

## <img width="4%" title="Allure Report" src="src/test/resources/img/logo/Allure_Report.svg"> Отчет о результатах тестирования в [Allure Report](https://jenkins.autotests.cloud/job/regresInAPI_tests/1/allure/#suites/2a99c9be34f1b0071cd237aacc339ae0/4c7db381de00c955/)

<p align="center">
  <img src="src/test/resources/img/screenshots/AllureReport.png" alt="allure-report1" width="900">
</p>


## <img width="4%" title="Allure TestOPS" src="src/test/resources/img/logo/AllureTestOps.svg"> Интеграция с [Allure TestOps](https://allure.autotests.cloud/launch/19629)

### Основной дашборд

<p align="center">
  <img src="src/test/resources/img/screenshots/AllureTestOps.png" alt="dashboard" width="900">
</p>

### Список тестов с результатами прогона

<p align="center">
  <img src="src/test/resources/img/screenshots/AllureTest.png" alt="dashboard" width="900">
</p>

### Список тест-кейсов, сформированных в результате прогона

<p align="center">
  <img src="src/test/resources/img/screenshots/testCases.png" alt="dashboard" width="900">
</p>

## <img width="4%" title="Telegram" src="src/test/resources/img/logo/Telegram.svg"> Уведомления в Telegram с использованием бота
После завершения сборки специальный бот, созданный в <code>Telegram</code>, автоматически обрабатывает и отправляет сообщение с отчетом о прогоне тестов.

<p align="center">
<img title="Telegram Notifications" src="src/test/resources/img/screenshots/Telegram.png">


</p>