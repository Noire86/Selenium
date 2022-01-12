package ru.ibs.myframework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.myframework.util.PageUtils;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    PageUtils pageUtils;


    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.pageUtils = new PageUtils(driver, wait);
    }
}
