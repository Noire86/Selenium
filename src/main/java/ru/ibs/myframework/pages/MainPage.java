package ru.ibs.myframework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BasePage {

    @FindBy(xpath = "//nav[@class=\"navigation\"]//a[contains(@class, text--second)]")
    private List<WebElement> navMenu;


    @Step("Тест")
    public CompaniesPage selectNavMenu(String btnText) {
        pageUtils.clickItemFromList(btnText, navMenu);
        return pageManager.getCompaniesPage();
    }
}
