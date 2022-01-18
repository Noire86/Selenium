package ru.ibs.myframework.pageobjects.dns;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.myframework.pageobjects.BasePage;
import ru.ibs.myframework.pageobjects.dns.models.Product;
import ru.ibs.myframework.pageobjects.dns.models.handlers.ProductHandler;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(xpath = "//div[@class=\"cart-items__product\"]")
    private List<WebElement> cartItems;

    @FindBy(xpath = "//div[@class=\"total-amount__label\"]//span[@class=\"price__current\"]")
    private WebElement totalPrice;

    @FindBy(xpath = "//div[@class=\"total-amount__count\"]")
    private WebElement totalAmount;

    @FindBy(xpath = "//span[contains(@class, \"radio-button__icon_checked\")]")
    private WebElement checkedRadio;

    @FindBy (xpath = "//div[@class=\"cart-tab\"]//span[@class=\"restore-last-removed\"]")
    private WebElement restoreRemovedItem;

    private final ProductHandler productHandler = ProductHandler.getInstance();

    public CartPage checkWarranty(String productName, int warrantyValue) {

        WebElement cartItem = pageUtils.getElementByAttributeContains("textContent", productName, cartItems);
        WebElement warrantyBlock = cartItem.findElement(By.xpath(".//div[@class=\"additional-warranties-row cart-items__additionals-item\"]"));
        WebElement checkedWarranty = warrantyBlock.findElement(By.xpath(".//span[contains(@class, \"radio-button__icon_checked\")]/.."));

        boolean warrantyBlockExists = warrantyBlock.isEnabled();
        boolean isCorrect = checkedWarranty.getAttribute("textContent").contains(String.valueOf(warrantyValue));

        Assertions.assertTrue(warrantyBlockExists, "У товара " + productName + " отсутствует блок доп. гарантии");
        Assertions.assertTrue(isCorrect, "Выбрано неверное значение доп.гарантии. Ожидалось: " + warrantyValue);

        return pageManager.getPage(CartPage.class);
    }

    public CartPage checkPrice(String productName) {
        WebElement el = pageUtils.getElementByAttributeContains("textContent", productName, cartItems);
        Product pr = productHandler.getProductByName(productName);
        int cartItemPrice = pageUtils.textAsInt(el.findElement(By.xpath(".//span[@class=\"price__current\"]")));
        int localItemPrice = (pr.getPrice() - pr.getWarrantyPrice());
        Assertions.assertEquals(cartItemPrice, localItemPrice, "Расхождение цены в корзине и ProductHandler.productList");
        return pageManager.getPage(CartPage.class);
    }

    public CartPage checkSummary() {
        int result = 0;
        int currentItemPrice = 0;
        int currentItemAmount;
        int currentItemWarranty = 0;
        int total = pageUtils.textAsInt(totalPrice);

        for (WebElement el : cartItems) {
            currentItemAmount = pageUtils.attributeAsInt(el.findElement(By.xpath(".//input[@class=\"count-buttons__input\"]")), "value");
            currentItemPrice += pageUtils.textAsInt(el.findElement(By.xpath(".//span[@class=\"price__current\"]")));

            try {
                currentItemWarranty += pageUtils.textAsInt(el.findElement(By.xpath(".//span[contains(@class, \"radio-button__icon_checked\")]/../..//span[@class=\"additional-warranties-row__price\"]")));
                currentItemWarranty *= currentItemAmount;
            } catch (NoSuchElementException ignored) {
                System.out.println("BAAAAAAAAAAAAAAAAAAAANG");
            }

        }
        result = (currentItemPrice + currentItemWarranty);

        Assertions.assertEquals(total, result, "Расхождение общей цены и суммы всех товаров");
        return pageManager.getPage(CartPage.class);
    }

    public CartPage deletePosition(String productName) {
        boolean success = false;
        int cartCost = pageUtils.textAsInt(totalPrice);
        int currentItemCost = 0;
        int newCartCost = 0;

        for (WebElement el : cartItems) {
            WebElement child = el.findElement(By.xpath(".//div[@class=\"cart-items__product-name\"]"));
            if ((child.getAttribute("textContent").toLowerCase()).contains(productName.toLowerCase())) {
                try {
                    currentItemCost = pageUtils.textAsInt(el.findElement(By.xpath(".//span[@class=\"price__current\"]")));
                    pageUtils.click(el.findElement(By.xpath(".//button[@class=\"menu-control-button remove-button\"]")));
                    newCartCost = pageUtils.textAsInt(totalPrice);

                    Assertions.assertEquals(newCartCost, (cartCost - currentItemCost), "Расхождение цены после удаления товара");

                    success = true;
                    break;
                } catch (Exception e) {
                    Assertions.fail("Не найдена кнопка удаления товара");
                }

            }
        }
        Assertions.assertTrue(success, "Товар " + productName + " не удалился из корзины!");
        return pageManager.getPage(CartPage.class);
    }

    public CartPage addItem(String productName, int times) {
        int newAmount = pageUtils.textAsInt(totalAmount);
        WebElement plus = null;

        for (WebElement el : cartItems) {
            WebElement child = el.findElement(By.xpath(".//div[@class=\"cart-items__product-name\"]"));
            if ((child.getAttribute("textContent").toLowerCase()).contains(productName.toLowerCase())) {

                try {
                    plus = el.findElement(By.xpath(".//button[contains(@class, \"button_plus\")]"));
                } catch (Exception e) {
                    Assertions.fail("Не найдена кнопка добавления товара");
                }
            }
        }

        Assertions.assertNotNull(plus);

        for (int i = 0; i < times; i++) {
            System.out.println("CLICKED");
            newAmount++;
            pageUtils.click(plus);
            wait.until(ExpectedConditions.attributeContains(totalAmount, "textContent", String.valueOf(newAmount)));
        }
        return pageManager.getPage(CartPage.class);
    }

    public CartPage restoreRemovedItem() {
        int preSize = cartItems.size();
        pageUtils.click(restoreRemovedItem);

        Assertions.assertNotEquals(preSize, cartItems.size(), "Не возвращен удаленный товар");
        return pageManager.getPage(CartPage.class);
    }
}
