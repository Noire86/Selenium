package ru.ibs.myframework.pageobjects.dns;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.ibs.myframework.pageobjects.BasePage;

import java.util.List;

public class SearchListPage extends BasePage {

    @FindBy(xpath = "//div[@data-id=\"product\"]")
    private List<WebElement> productsBlocks;

    @FindBy(xpath = "//div[@data-id=\"product\"]//a[contains(@class, \"catalog-product__name\")]//span/..")
    private List<WebElement> productLinks;


    @Step
    public ProductPage clickProduct(String titleContains) {
        pageUtils.click(pageUtils.getElementByAttributeContains("text", titleContains, productLinks));
        return pageManager.getPage(ProductPage.class);
    }





}
