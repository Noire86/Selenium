package ru.ibs.tests.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import ru.ibs.myframework.managers.DriverManager;
import ru.ibs.myframework.managers.PageManager;

public class Base {
    DriverManager driverManager = DriverManager.getInstance();

    @BeforeAll
    static void beforeAll() {}

    @BeforeEach
    void beforeEach() {
        driverManager.getDriver().manage().window().maximize(); //потом перенести в .properties
        driverManager.getDriver().get("https://rgs.ru");
    }

    @AfterEach
    void afterEach() {
        driverManager.quitDriver();
    }

    @AfterAll
    static void afterAll() {
    }
}
