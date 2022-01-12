package ru.ibs.tests.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.ibs.myframework.managers.DriverManager;

public class Base {

    private static final DriverManager driverManager = DriverManager.getInstance();

    @BeforeAll
    static void beforeAll() {}

    @BeforeEach
    void beforeEach() {
        driverManager.getDriver().manage().deleteAllCookies();
        driverManager.getDriver().get("https://rgs.ru");
    }

    @AfterEach
    void afterEach() {
    }

    @AfterAll
    static void afterAll() {
        driverManager.getDriver().quit(); //закроем драйвер
        //dummy
    }
}
