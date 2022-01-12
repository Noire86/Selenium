package ru.ibs.tests.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.myframework.pages.CompaniesPage;
import ru.ibs.myframework.pages.DMSPage;
import ru.ibs.myframework.pages.MainPage;
import ru.ibs.myframework.util.DriverManager;

public class Base {

    private WebDriver driver = DriverManager.getInstance().getDriver();

    @BeforeAll
    static void beforeAll() {}

    @BeforeEach
    void beforeEach() {
        driver.get("https://rgs.ru");
    }

    @AfterEach
    void afterEach() {
        driver.quit(); //закроем драйвер
    }

    @AfterAll
    static void afterAll() {
        //dummy
    }
}
