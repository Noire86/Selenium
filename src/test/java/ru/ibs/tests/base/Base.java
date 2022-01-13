package ru.ibs.tests.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.ibs.myframework.managers.DriverManager;
import ru.ibs.myframework.managers.PageManager;

public class Base {

    @BeforeAll
    static void beforeAll() {}

    @BeforeEach
    void beforeEach() {
       DriverManager.getInstance().getDriver().get("https://rgs.ru");
    }

    @AfterEach
    void afterEach() {
        DriverManager.getInstance().quitDriver();
    }

    @AfterAll
    static void afterAll() {
    }
}
