package ru.ibs.myframework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.myframework.managers.DriverManager;
import ru.ibs.myframework.managers.PageManager;
import ru.ibs.myframework.util.PageUtils;

public class BasePage {
    protected WebDriver driver = DriverManager.getInstance().getDriver();
    protected WebDriverWait wait = DriverManager.getInstance().getDriverWait();
    protected PageManager pageManager = PageManager.getInstance();
    protected PageUtils pageUtils = new PageUtils();

    public BasePage() {
        PageFactory.initElements(DriverManager.getInstance().getDriver(), this);
    }


}
