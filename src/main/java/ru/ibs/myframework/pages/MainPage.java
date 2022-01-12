package ru.ibs.myframework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BasePage {

    @FindBy(xpath = "//nav[@class=\"navigation\"]//a[contains(@class, text--second)]")
    private List<WebElement> navMenu;

    public CompaniesPage selectNavMenu(String btnText) {
        pageUtils.clickItemFromList(btnText, navMenu);
        return pageManager.getCompaniesPage();
    }
}
