package ru.ibs.myframework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CompaniesPage extends BasePage {

    @FindBy(xpath = "//section[contains(@class, \"section-categories\")]//li[@class=\"item text--second\"]")
    private List<WebElement> baseMenu;

    @FindBy(xpath = "//div[@class='header-list-products']//a")
    private List<WebElement> healthMenu;


    public CompaniesPage() {
        PageFactory.initElements(driver, this);

    }


    public void selectBaseMenuButton(String btnText) {
        pageUtils.clickItemFromList(btnText, baseMenu);
    }

    public void selectHealthMenuButton(String btnText) {
        pageUtils.clickItemFromList(btnText, healthMenu);
    }

    public void checkCompaniesPageTitle(String contains, String expectedTitle) {
        pageUtils.assertTitle(contains, expectedTitle);
    }
}