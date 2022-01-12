package ru.ibs.myframework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage extends BasePage {

    @FindBy(xpath = "//nav[@class=\"navigation\"]//a[contains(@class, text--second)]")
    private List<WebElement> navMenu;

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }


    public void selectNavMenu(String btnText) {
        pageUtils.selectItemFromList(btnText, navMenu);
    }
}
