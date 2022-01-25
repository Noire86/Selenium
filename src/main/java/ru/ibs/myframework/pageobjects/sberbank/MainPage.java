package ru.ibs.myframework.pageobjects.sberbank;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.myframework.managers.PageManager;
import ru.ibs.myframework.pageobjects.BasePage;

import java.util.List;

public class MainPage extends BasePage {

    @FindBy(xpath = "//li[@class=\"kitt-top-menu__item kitt-top-menu__item_first\"]")
    List<WebElement> topMenuElements;

    @FindBy(xpath = "//li[@class=\"kitt-top-menu__item kitt-top-menu__item_first kitt-top-menu__item_opened\"]//li")
    List<WebElement> subMenuElements;

    @FindBy(xpath = "//button[@class='kitt-cookie-warning__close']")
    private WebElement cookies;




    public MainPage closeCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(cookies));
        pageUtils.click(cookies);

        return pageManager.getPage(MainPage.class);
    }

    public MainPage selectTopMenu(String button) {
        pageUtils.click(pageUtils.getElementByAttributeEquals("outerText", button, topMenuElements));
        return pageManager.getPage(MainPage.class);
    }


    public MainPage selectSubMenu(String button) {
        pageUtils.click(pageUtils.getElementByAttributeEquals("textContent", button, subMenuElements, true));
        return pageManager.getPage(MainPage.class);
    }

    public CreditPage goToCreditPage() {
        return pageManager.getPage(CreditPage.class);
    }

}
