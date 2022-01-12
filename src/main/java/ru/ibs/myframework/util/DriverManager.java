package ru.ibs.myframework.util;

import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;


public class DriverManager {

    private static DriverManager INSTANCE;
    private static WebDriver driver;

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
            if (OS.isFamilyWindows()) {
                driver = getBrowserDriver("Windows");
            } else if (OS.isFamilyMac()) {
                driver = getBrowserDriver("Mac");
            } else if (OS.isFamilyUnix()) {
                driver = getBrowserDriver("Linux");
            }
            driver.manage().window().maximize();
        }
        return driver;
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
                System.setProperty("webdriver.opera.driver", "src/test/resources/operadriver" + extension);
                return new OperaDriver();
            case "firefox":
                System.setProperty("webdriver.firefox.driver", "src/test/resources/firefoxdriver" + extension);
                return new FirefoxDriver();
            default:
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver" + extension);
                return new ChromeDriver();
        }
    }


}
