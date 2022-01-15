package ru.ibs.myframework.pageobjects.dns;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.myframework.pageobjects.BasePage;
import ru.ibs.myframework.pageobjects.dns.models.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//h1[@class=\"product-card-top__title\"]")
    private WebElement productName;

    @FindBy(xpath = "//div[@class=\"product-card-top__code\"]")
    private WebElement productCode;

    @FindBy(xpath = "//div[@class=\"product-card-top__buy\"]/div/div/div[contains(@class, \"product-buy__price\")]")
    private WebElement productPrice;

    @FindBy(xpath = "//div[@class=\"product-card-top__specs\"]")
    private WebElement productBaseSpecs;

    @FindBy(xpath = "//div[@class=\"additional-sales-tabs__titles-wrap\"]//div")
    private List<WebElement> accessoriesList;

    @FindBy(xpath = "//div[@class=\"product-warranty__items\"]//label")
    private List<WebElement> warrantyItems;

    @FindBy(xpath = "//div[@class=\"product-card-top__buy\"]//button[contains(@class, \"buy-btn\")]")
    private WebElement buyButton;

    @FindBy(xpath = "//a[@class=\"ui-link cart-link\"]")
    private WebElement cartButton;

    @FindBy(xpath = "//span[@class=\"cart-link__price\"]")
    private WebElement cartLinkPrice;

    @FindBy(xpath = "//div[@class=\"product-card-top__buy\"]//button[text()=\"В корзине\"]")
    private WebElement inCartButton;


    private final List<Product> productList = new ArrayList<>();

    public ProductPage clickWarrantyMenu() {
        pageUtils.click(pageUtils.getElementByAttributeContains("textContent", "Гарантия", accessoriesList));
        return pageManager.getPage(ProductPage.class);
    }

    public ProductPage clickWarrantyRadioButton(String attributeValue) {
        pageUtils.click(pageUtils.getElementByAttributeContains("textContent", attributeValue, warrantyItems));
        return pageManager.getPage(ProductPage.class);
    }

    public ProductPage clickBuy() {
        pageUtils.click(buyButton);
        return pageManager.getPage(ProductPage.class);
    }

    public CartPage clickCart() {
        pageUtils.click(cartButton);
        return pageManager.getPage(CartPage.class);
    }

    public ProductPage checkSummary() {
        int productsSummary = 0;
        for(Product pr : productList) {
            productsSummary += pr.getPrice();
        }

        wait.until(ExpectedConditions.visibilityOf(inCartButton));

        Assertions.assertEquals(Integer.parseInt(cartLinkPrice.getText().replaceAll("\\D+", "")), productsSummary);
        return pageManager.getPage(ProductPage.class);
    }

    public SearchFieldPage goToSearchField() {
        return pageManager.getPage(SearchFieldPage.class);
    }


    public ProductPage updateProductPrice(String productName) {
        for (Product pr : productList) {
            if (pr.getName().toLowerCase().contains(productName)) {
                pr.setPrice(Integer.parseInt(productPrice.getText().replaceAll("\\D+", "")));
            }
        }
        return pageManager.getPage(ProductPage.class);
    }


    public ProductPage createProductModel(boolean hasWarranty) {

        wait.until(ExpectedConditions.visibilityOf(productName));
        String name = productName.getText();

        wait.until(ExpectedConditions.visibilityOf(productCode));
        String code = productCode.getText().replace("Код товара: ", "");

        wait.until(ExpectedConditions.visibilityOf(productPrice));
        int price = Integer.parseInt(productPrice.getText().trim().replaceAll("\\D+", ""));

        wait.until(ExpectedConditions.visibilityOfAllElements(productBaseSpecs));
        List<String> basicSpecs = Arrays.asList(productBaseSpecs.getText().split(","));

        int warranty = 0;
        if (hasWarranty) {
            wait.until(ExpectedConditions.visibilityOfAllElements(accessoriesList));
            warranty = Integer.parseInt(pageUtils.getElementByAttributeContains("textContent", "Гарантия", accessoriesList)
                    .getText()
                    .trim()
                    .replaceAll("\\D+", ""));
        }

        productList.add(new Product(name, code, price, warranty, basicSpecs));

        return pageManager.getPage(ProductPage.class);

    }
}
