package ru.ibs.myframework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CompaniesPage extends BasePage {

    @FindBy(xpath = "//section[contains(@class, \"section-categories\")]//li[@class=\"item text--second\"]")
    private List<WebElement> baseMenu;

    @FindBy(xpath = "//div[@class='header-list-products']//a")
    private List<WebElement> healthMenu;


    public CompaniesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }


    public void selectBaseMenuButton(String btnText) {
        pageUtils.selectItemFromList(btnText, baseMenu);
    }

    public void selectHealthMenuButton(String btnText) {
        pageUtils.selectItemFromList(btnText, healthMenu);
    }

    public void checkCompaniesPageTitle(String contains, String expectedTitle) {
        pageUtils.assertTitle(contains, expectedTitle);
    }
}