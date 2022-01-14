package ru.ibs.myframework.managers;

import org.junit.jupiter.api.condition.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;


public class DriverManager {

    private static DriverManager INSTANCE;
    private WebDriver driver;

    private DriverManager() {
    }

    public static DriverManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }


    public WebDriver getDriver() {
        if (driver == null) {
            if (OS.WINDOWS.isCurrentOs()) {
                driver = getBrowserDriver("Windows");
            } else if (OS.LINUX.isCurrentOs()) {
                driver = getBrowserDriver("Linux");
            } else if (OS.MAC.isCurrentOs()) {
                driver = getBrowserDriver("Mac");
            }
            return driver;

        }
        return driver;
    }

    public void quitDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }


    private WebDriver getBrowserDriver(String os) {
        String browser = System.getProperty("browser", "chrome");
        String extension;

        switch (os) {
            case "Windows":
                extension = ".exe";
                break;
            case "Mac":
            case "Linux":
                extension = "";
                break;
            default:
                return null;
        }

        switch (browser) {
            case "opera":
                System.setProperty("webdriver.opera.driver", "src/main/resources/operadriver" + extension);
                return new OperaDriver();
            case "firefox":
                System.setProperty("webdriver.firefox.driver", "src/main/resources/firefoxdriver" + extension);
                return new FirefoxDriver();
            default:
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver" + extension);
                return new ChromeDriver();
        }
    }


}
