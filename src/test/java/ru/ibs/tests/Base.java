package ru.ibs.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import ru.ibs.myframework.listeners.AllureListener;
import ru.ibs.myframework.managers.DriverManager;
import ru.ibs.myframework.managers.PageManager;
import ru.ibs.myframework.managers.PropertiesManager;
import ru.ibs.myframework.util.PropertyKey;

@ExtendWith(AllureListener.class)
public class Base {
    DriverManager driverManager = DriverManager.getInstance();
    PropertiesManager propertiesManager = PropertiesManager.getInstance();

    @BeforeAll
    static void beforeAll() {}

    @BeforeEach
    void beforeEach() {
        driverManager.getDriver().get(propertiesManager.getProperty(PropertyKey.SITE_ADDRESS));
    }

    @AfterEach
    void afterEach() {
        driverManager.quitDriver();
    }

    @AfterAll
    static void afterAll() {
    }
}
