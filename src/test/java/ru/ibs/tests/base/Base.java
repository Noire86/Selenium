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
import ru.ibs.myframework.util.DriverUtils;

public class Base {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected MainPage mainPage;
    protected CompaniesPage companiesPage;
    protected DMSPage dmsPage;

    @BeforeAll
    static void beforeAll() {}

    @BeforeEach
    void beforeEach() {
        driver = DriverUtils.getAnyDriver(System.getProperty("browser", "opera"));
        wait = new WebDriverWait(driver, 20, 2500);

        mainPage = new MainPage(driver, wait);
        companiesPage = new CompaniesPage(driver, wait);
        dmsPage = new DMSPage(driver, wait);

        driver.manage().window().maximize();
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
