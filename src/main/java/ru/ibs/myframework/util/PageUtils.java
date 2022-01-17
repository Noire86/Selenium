package ru.ibs.myframework.util;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.myframework.managers.DriverManager;

import java.util.List;

public class PageUtils {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;

    public PageUtils() {
        this.driver = DriverManager.getInstance().getDriver();
        this.wait = DriverManager.getInstance().getDriverWait();
        this.actions = new Actions(driver);
    }

    public void hover(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        actions.moveToElement(element);
    }

    public void click(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    public void clickViaActions(WebElement element) {
        actions.moveToElement(element)
                .click(element)
                .build()
                .perform();
    }

    public WebElement clickItemFromList(String value, List<WebElement> list) {
        for (WebElement item : list) {
            if (item.getText().contains(value)) {
                wait.until(ExpectedConditions.elementToBeClickable(item));
                item.click();
                return item;
            }
        }
        Assertions.fail("No button with name " + value + " were found ");
        return null;
    }

    public void fillInput(WebElement element, String value) {
        scrollJS(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        element.clear();
        element.sendKeys(value);

        //конвертация вводимого номера в масочный формат, т.к атрибут value содержит уже отформатированное значение
        String number = value.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "+7 ($1) $2-$3");
        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", number));
        Assertions.assertTrue(checkFlag, "Поле было заполнено некорректно"); //проверка
    }

    public void fillInputPhone(WebElement element, String value) {
        scrollJS(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        element.clear();
        element.sendKeys(value);
        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", value));
        Assertions.assertTrue(checkFlag, "Поле было заполнено некорректно");
    }

    public void scrollJS(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void assertTitle(String contains, String expectedTitle) {
        wait.until(ExpectedConditions.titleContains(contains)); //Проверяем изменился ли заголовок после нажатия кнопки Компании
        Assertions.assertEquals(expectedTitle, driver.getTitle()); //Проверка на корректность заголовка страницы
    }

    public void assertErrorField(WebElement errorField, String errorMsg) {
        wait.until(ExpectedConditions.visibilityOf(errorField));
        Assertions.assertEquals(errorMsg, errorField.getText());
    }

    public void assertElementVisibility(WebElement element, String assertMsg) {
        Assertions.assertTrue(element.isDisplayed(), assertMsg);
    }

    public void assertElementVisibility(List<WebElement> list, String text, String assertMsg) {
        for (WebElement item : list) {
            if (item.getText().contains(text)) {
                Assertions.assertTrue(item.isDisplayed(), assertMsg);
                return;
            }
        }
    }

    public WebElement getElementByAttributeEquals(String attribute, String value, List<WebElement> list) {
        wait.until(ExpectedConditions.visibilityOfAllElements(list));

        for (WebElement el : list) {
            if (el.getAttribute(attribute).equalsIgnoreCase(value)) {
                return el;
            }
        }
        return null;
    }


    public WebElement getElementByAttributeContains(String attribute, String value, List<WebElement> list) {
        wait.until(ExpectedConditions.visibilityOfAllElements(list));

        for (WebElement el : list) {
            if (el.getAttribute(attribute).toLowerCase().contains(value.toLowerCase())) {
                return el;
            }
        }
        return null;
    }


}
