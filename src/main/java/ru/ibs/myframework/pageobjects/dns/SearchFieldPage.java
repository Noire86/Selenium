package ru.ibs.myframework.pageobjects.dns;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.myframework.pageobjects.BasePage;

import java.util.List;

public class SearchFieldPage extends BasePage {

    @FindBy(xpath = "//form[@data-role=\"presearch-form\"]//input[not(@id)]")
    private WebElement searchField;


    @FindBy(xpath = "//div[@class=\"presearch__suggests\"]//a")
    private List<WebElement> suggestions;

    @FindBy(xpath = "//h1[@class=\"title\"]")
    private WebElement h1;

    @Step
    public SearchFieldPage fillSearch(String request) {
        pageUtils.hover(searchField);
        pageUtils.fillInput(searchField, request);
        return pageManager.getPage(SearchFieldPage.class);
    }
    @Step
    public SearchFieldPage selectSuggestion(String value) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class=\"presearch__suggests\"]//a[not (contains(@class, \"history\"))]")));
        pageUtils.click(pageUtils.getElementByAttributeEquals("text", value, suggestions));
        return pageManager.getPage(SearchFieldPage.class);
    }

    @Step
    public SearchListPage checkSearchPageH1() {
        wait.until(ExpectedConditions.visibilityOf(h1));
        pageUtils.assertElementVisibility(h1, "Не обнаружен заголовок страницы поиска товара");
        return pageManager.getPage(SearchListPage.class);
    }
}
