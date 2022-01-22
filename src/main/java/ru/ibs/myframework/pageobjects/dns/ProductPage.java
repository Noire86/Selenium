package ru.ibs.myframework.pageobjects.dns;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.myframework.pageobjects.BasePage;
import ru.ibs.myframework.pageobjects.dns.models.Product;
import ru.ibs.myframework.pageobjects.dns.models.handlers.ProductHandler;

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
    private List<WebElement> additionalSales;

    @FindBy(xpath = "//div[@class=\"product-warranty__items\"]//label")
    private List<WebElement> warrantyLabels;

    @FindBy(xpath = "//div[@class=\"product-warranty__items\"]//input")
    private List<WebElement> warrantyInputs;

    @FindBy(xpath = "//div[@class=\"product-warranty__items\"]//span[@class=\"product-warranty__period\"]")
    private List<WebElement> warrantyPeriods;

    @FindBy(xpath = "//div[@class=\"product-card-top__buy\"]//button[contains(@class, \"buy-btn\")]")
    private WebElement buyButton;

    @FindBy(xpath = "//a[@class=\"ui-link cart-link\"]")
    private WebElement cartButton;

    @FindBy(xpath = "//span[@class=\"cart-link__price\"]")
    private WebElement cartLinkPrice;

    @FindBy(xpath = "//div[@class=\"product-card-top__buy\"]//button[text()=\"В корзине\"]")
    private WebElement inCartButton;

    @FindBy(xpath = "//span[@class=\"cart-link__badge\"]")
    private WebElement cartCounter;

    private final ProductHandler productHandler = ProductHandler.getInstance();


    private WebElement getWarrantyBox() {
        WebElement warrantyButton = pageUtils.getElementByAttributeContains("textContent", "Гарантия", additionalSales);
        wait.until(ExpectedConditions.visibilityOf(warrantyButton));
        Assertions.assertTrue(warrantyButton.isDisplayed(), "Отсутствует меню выбора доп. гарантии");
        return warrantyButton;
    }
    @Step("Клик по подменю 'Гарантия'")
    public ProductPage clickWarrantyMenu() {
        pageUtils.click(getWarrantyBox());
        return pageManager.getPage(ProductPage.class);
    }
    @Step("Выбор параметра гарантии с параметром {period}")
    public ProductPage clickWarrantyRadioButton(String period) {
        WebElement label = pageUtils.getElementByAttributeContains("textContent", period, warrantyPeriods, true).findElement(By.xpath("./../.."));
        pageUtils.assertElementVisibility(label, "Не обнаружена кнопка выбора гарантии. Ожидалось наличие элемента " + label + ":");
        pageUtils.click(label);


        return pageManager.getPage(ProductPage.class);
    }

    @Step("Обновление значений гарантии внутри модели товара {productName}")
    public ProductPage updateProductWarranty(String productName) {
        Product product = productHandler.getProductByName(productName);
        Assertions.assertNotEquals("dummy", product.getName(), "Не удалось найти товар, содержащий в наименовании " + productName);
        WebElement selectedLabel = pageUtils.getSelectedElement(warrantyInputs).findElement(By.xpath("./.."));

        int warrantyPrice = pageUtils.textAsInt(selectedLabel.findElement(By.xpath(".//span[@class=\"product-warranty__price\"]")));
        int warrantyMonths = pageUtils.textAsInt(selectedLabel.findElement(By.xpath(".//span[@class=\"product-warranty__period\"]")));

        product.setWarrantyPrice(warrantyPrice);
        product.setWarrantyMonths(warrantyMonths);

        return pageManager.getPage(ProductPage.class);
    }
    @Step("Клик по кнопке Купить")
    public ProductPage clickBuy() {
        pageUtils.click(buyButton);
        return pageManager.getPage(ProductPage.class);
    }
    @Step("Переход в корзину покупок")
    public CartPage clickCart() {
        pageUtils.click(cartButton);
        return pageManager.getPage(CartPage.class);
    }
    @Step("Проверка суммы приобретенного товара")
    public ProductPage checkSummary() {
        int cartAmount = Integer.parseInt(cartCounter.getAttribute("textContent")) + 1;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"cart-link__badge\"]")));
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//span[@class=\"cart-link__badge\"]"), "textContent", String.valueOf(cartAmount)));


        Assertions.assertEquals(pageUtils.textAsInt(cartLinkPrice), ProductHandler.getInstance().getSummaryPrice());
        return pageManager.getPage(ProductPage.class);
    }

    public SearchFieldPage goToSearchField() {
        return pageManager.getPage(SearchFieldPage.class);
    }

    @Step("Обновление цены внутри модели товара {productName}")
    public ProductPage updateProductPrice(String productName) {
        Product product = productHandler.getProductByName(productName);
        Assertions.assertNotNull(product, "Не удалось найти товар, содержащий в наименовании " + productName);

        product.setPrice(pageUtils.textAsInt(productPrice));

        return pageManager.getPage(ProductPage.class);
    }

    @Step("Создание модели товара")
    public ProductPage createProductModel() {

        wait.until(ExpectedConditions.visibilityOf(productName));
        String name = productName.getText();

        wait.until(ExpectedConditions.visibilityOf(productCode));
        String code = productCode.getText().replace("Код товара: ", "");

        wait.until(ExpectedConditions.visibilityOf(productPrice));
        int price = pageUtils.textAsInt(productPrice);

        wait.until(ExpectedConditions.visibilityOfAllElements(productBaseSpecs));
        List<String> basicSpecs = Arrays.asList(productBaseSpecs.getText().split(","));

        productHandler.put(new Product(name, code, price, 0, 0, basicSpecs));

        return pageManager.getPage(ProductPage.class);
    }
}
