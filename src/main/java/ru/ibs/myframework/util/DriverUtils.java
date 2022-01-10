package ru.ibs.myframework.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

//утилитарный метод выбора нужного драйвера

public class DriverUtils {
    public static WebDriver getAnyDriver(String browser) {

        System.setProperty("webdriver." + browser + ".driver", "src/test/resources/" + browser + "driver.exe");

        if (browser.contains("chrome")) {
            return new ChromeDriver();
        } else if (browser.contains("firefox")) {
            return new FirefoxDriver();
        }

        return new OperaDriver();

    }
}
