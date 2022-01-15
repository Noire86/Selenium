package ru.ibs.myframework.managers;

import org.junit.jupiter.api.condition.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import ru.ibs.myframework.util.PropertyKey;


public final class DriverManager {

    private WebDriver driver;
    private final PropertiesManager propertiesManager = PropertiesManager.getInstance();
    private final PageManager pageManager = PageManager.getInstance();

    private DriverManager() {
    }

    private static class Holder {
        public static final DriverManager INSTANCE = new DriverManager();
    }

    public static DriverManager getInstance() {
        return Holder.INSTANCE;
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

            if (propertiesManager.getProperty(PropertyKey.MAXIMIZED).equals("true")) {
                driver.manage().window().maximize();
            }

            return driver;

        }
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            pageManager.clearPages();
            driver = null;
        }
    }


    private WebDriver getBrowserDriver(String os) {
        String browser = propertiesManager.getProperty(PropertyKey.BROWSER_TYPE, "chrome");
        String driverPath = propertiesManager.getProperty(PropertyKey.DRIVER_DIR, "src/main/resources/drivers/");
        String extension;

        if (os.equals("Windows")) {
            extension = ".exe";
        } else if (os.equals("Mac") || os.equals("Linux")) {
            extension = "";
        } else {
            //err.log("Error finding OS type");
            return null;
        }

        switch (browser) {
            case "opera":
                System.setProperty("webdriver.opera.driver", driverPath + browser + "driver" + extension);
                return new OperaDriver();
            case "firefox":
                System.setProperty("webdriver.firefox.driver", driverPath + browser + "driver" + extension);
                return new FirefoxDriver();
            case "chrome":
            default:
                System.setProperty("webdriver.chrome.driver", driverPath + browser + "driver" + extension);
                return new ChromeDriver();
        }
    }


}
