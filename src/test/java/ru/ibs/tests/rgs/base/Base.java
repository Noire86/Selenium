package ru.ibs.tests.rgs.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import ru.ibs.myframework.managers.DriverManager;
import ru.ibs.myframework.managers.PageManager;
import ru.ibs.myframework.managers.PropertiesManager;
import ru.ibs.myframework.util.PropertyKey;

public class Base {
    DriverManager driverManager = DriverManager.getInstance();

    @BeforeAll
    static void beforeAll() {}

    @BeforeEach
    void beforeEach() {
        driverManager.getDriver().get(PropertiesManager.getInstance().getProperty(PropertyKey.SITE_ADDRESS));
    }

    @AfterEach
    void afterEach() {
        driverManager.quitDriver();
    }

    @AfterAll
    static void afterAll() {
    }
}
