package ru.ibs.myframework.pageobjects;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.myframework.managers.DriverManager;
import ru.ibs.myframework.managers.PageManager;
import ru.ibs.myframework.util.PageUtils;

public class BasePage {
    protected PageManager pageManager = PageManager.getInstance();
    protected PageUtils pageUtils = new PageUtils();
    protected WebDriverWait wait = new WebDriverWait(DriverManager.getInstance().getDriver(), 20, 2000);

    public BasePage() {
        PageFactory.initElements(DriverManager.getInstance().getDriver(), this);
    }


}
