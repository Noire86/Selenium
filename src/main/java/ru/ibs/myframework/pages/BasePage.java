package ru.ibs.myframework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.myframework.util.pageutils.PageUtils;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected PageUtils pageUtils;


    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.pageUtils = new PageUtils(driver, wait);
    }
}
