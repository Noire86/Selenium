package ru.ibs.myframework.pages;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.myframework.managers.DriverManager;
import ru.ibs.myframework.managers.PageManager;
import ru.ibs.myframework.managers.util.PageUtils;

public class BasePage {
    protected DriverManager driverManager = DriverManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();


    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 20, 2000);
    protected PageUtils pageUtils = new PageUtils(driverManager.getDriver(), wait);

    public BasePage() {
        PageFactory.initElements(DriverManager.getInstance().getDriver(), this);
    }


}
