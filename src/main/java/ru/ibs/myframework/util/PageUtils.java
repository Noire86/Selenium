package ru.ibs.myframework.util;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
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

    public void fillInputPhone(WebElement element, String value) {
        clearInput(element);
        element.sendKeys(value);

        String number = value.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "+7 ($1) $2-$3");
        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", number));
        Assertions.assertTrue(checkFlag, "Поле было заполнено некорректно"); //проверка
    }

    public void fillInput(WebElement element, String value) {
        clearInput(element);
        element.sendKeys(value);

        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", value));
        Assertions.assertTrue(checkFlag, "Поле было заполнено некорректно");
    }

    public void clearInput(WebElement element) {
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
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
        wait.until(ExpectedConditions.visibilityOf(element));
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
        for (WebElement el : list) {

            String s = el.getAttribute(attribute);

            if (el != null && el.getAttribute(attribute).equalsIgnoreCase(value)) {
                return el;
            }
        }
        throw new NoSuchElementException("Не найден элемент соответствующий точному параметру атрибута " + attribute);
    }

    public WebElement getElementByAttributeEquals(String attribute, String value, List<WebElement> list, boolean needWait) {
        if (needWait) {
            wait.until(ExpectedConditions.visibilityOfAllElements(list));
        }
        return getElementByAttributeEquals(attribute, value, list);
    }


    public WebElement getElementByAttributeContains(String attribute, String value, List<WebElement> list) {
        for (WebElement el : list) {
            if (el.getAttribute(attribute).toLowerCase().contains(value.toLowerCase())) {
                return el;
            }
        }
        throw new NoSuchElementException("Не найден элемент соответствующий приблизительному параметру атрибута " + attribute);
    }

    public WebElement getElementByAttributeContains(String attribute, String value, List<WebElement> list, boolean needWait) {
        if (needWait) {
            wait.until(ExpectedConditions.visibilityOfAllElements(list));
        }
        return getElementByAttributeContains(attribute,value,list);
    }


    public WebElement getSelectedElement(List<WebElement> elements) {
        for(WebElement e : elements) {
            if(e.isSelected()) {
                return e;
            }
        }
        throw new NoSuchElementException("Не найден элемент с состоянием Selected");
    }

    public int textAsInt(WebElement element) {
        return Integer.parseInt(element.getText().replaceAll("\\D+", ""));
    }

    public int attributeAsInt(WebElement element, String attribute) {
        return Integer.parseInt(element.getAttribute(attribute).replaceAll("\\D+", ""));
    }

    public int stringAsInt(String str) {
        return Integer.parseInt(str.replaceAll("\\D+", ""));
    }


}
