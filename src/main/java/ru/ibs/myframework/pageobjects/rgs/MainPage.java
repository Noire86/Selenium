package ru.ibs.myframework.pageobjects.rgs;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.ibs.myframework.pageobjects.BasePage;

import java.util.List;

public class MainPage extends BasePage {

    @FindBy(xpath = "//nav[@class=\"navigation\"]//a[contains(@class, text--second)]")
    private List<WebElement> navMenu;


  //  @Step("Тест")
    public CompaniesPage selectNavMenu(String btnText) {
        pageUtils.clickItemFromList(btnText, navMenu);
        return pageManager.getPage(CompaniesPage.class);
    }
}
