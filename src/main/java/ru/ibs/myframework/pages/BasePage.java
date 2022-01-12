package ru.ibs.myframework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.myframework.util.DriverManager;
import ru.ibs.myframework.util.pageutils.PageUtils;

public class BasePage {
    protected WebDriver driver = DriverManager.getInstance().getDriver();
    protected WebDriverWait wait = new WebDriverWait(driver, 20, 2000);
    protected PageUtils pageUtils = new PageUtils(driver, wait);



}
