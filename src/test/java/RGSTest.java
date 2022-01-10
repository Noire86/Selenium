import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class RGSTest extends BaseTest {

    @BeforeEach
    public void before() {
        driver.get("https://rgs.ru");
    }


    @Test
    public void test() {
        //выбираем категорию Компаниям
        WebElement companiesButton = driver.findElement(By.xpath("//a[@href='/for-companies']")); //обнаружили элемент
        wait.until(ExpectedConditions.elementToBeClickable(companiesButton));
        companiesButton.click(); //кликаем

        wait.until(ExpectedConditions.titleContains("компаний")); //Проверяем изменился ли заголовок после нажатия кнопки Компании
        Assertions.assertEquals("Страхование компаний и юридических лиц | Росгосстрах", driver.getTitle()); //Проверка на корректность заголовка страницы


        WebElement healthButton = driver.findElement(By.xpath("//span[contains(text(), 'Здоровье') and contains(@class, 'padding')]")); //обнаружили кнопку Здоровье
        wait.until(ExpectedConditions.elementToBeClickable(healthButton)); //Ожидаем пока кнопка будет доступна
        healthButton.click(); //кликаем


        WebElement dms = driver.findElement(By.xpath("//a[contains(@href, \"dobrovolnoe\")]")); //Обнаруживаем кнопку из меню Здоровье
        wait.until(ExpectedConditions.elementToBeClickable(dms)); //Дополнительная проверка на всякий случай
        wait.until(ExpectedConditions.visibilityOf(dms));
        Assertions.assertTrue(dms.isDisplayed(), "Не отображено!"); // Проверяем открылось ли окно Здоровье, а так же видимость пункта ДМС (2 в 1)
        dms.click(); //клац

        wait.until(ExpectedConditions.titleContains("Добровольное")); //Ждем пока заголовок не изменится на новый
        Assertions.assertEquals("Добровольное медицинское страхование для компаний и юридических лиц в Росгосстрахе", driver.getTitle()); //Верифицируем заголовок


        String appButtonXPath = "//button[contains(@class, \"action-item btn--basic\")]//span[contains(text(), \"Отправить заявку\")]";
        WebElement applicationButton = driver.findElement(By.xpath(appButtonXPath)); //Ищем кнопку отправки заявки
        wait.until(ExpectedConditions.elementToBeClickable(applicationButton)); //Ждем доступности кнопки отправки заявки
        applicationButton.click(); //клац

        WebElement h2 = driver.findElement(By.xpath("//h2[contains(@class, \"word-breaking title--h2\") and contains(text(), \"Оперативно перезвоним\")]"));
        Assertions.assertTrue(h2.isDisplayed(), "Отсутствует заголовок формы ввода данных!"); //Проверяем наличие формы


        try {
            Thread.sleep(1600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Заполняем поля методом со встроенными проверками, для телефона отдельный метод:
        fillInputField(driver.findElement(By.xpath("//input[contains(@name, \"userName\")]")), "Иванов Иван Иванович");
        fillInputFieldPhone(driver.findElement(By.xpath("//input[contains(@name, \"userTel\")]")), "9764554546");
        fillInputField(driver.findElement(By.xpath("//input[contains(@name, \"userEmail\")]")), "qwertyqwerty");
        fillInputField(driver.findElement(By.xpath("//input[@placeholder=\"Введите\"]")), "Омск");


        //прожимаем чекбокс
        WebElement checkbox = driver.findElement(By.xpath("//div[contains(@class, \"checkbox-body\")]/input"));
        actions.moveToElement(checkbox).click(checkbox).build().perform();

        //составляем List предложенных вариантов адреса, пробегаемся циклом и выбираем нужный
        List<WebElement> listSuggestions = driver.findElements(By.xpath("//input[@placeholder=\"Введите\"]/../..//span[contains(@class, item)]"));

        for (WebElement webElement : listSuggestions) {
            if (webElement.getText().contains("г Омск")) {
                webElement.click();
                break;
            }
        }

        //Находим кнопку отправки формы, нажимаем ее.
        WebElement submitButton = driver.findElement(By.xpath("//button[@type=\"submit\"][text()=\"Свяжитесь со мной\"]"));
        scrollToElementJs(submitButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type=\"submit\"][text()=\"Свяжитесь со мной\"]")));
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();

        //проверка того, что почтовый адрес неверен при отправке формы
        String errorMsg = "Введите корректный адрес электронной почты";
        boolean exists = driver.findElements(By.xpath("//span[contains(@class, 'input__error') and contains(text(), '" + errorMsg + "')]")).isEmpty();
        Assertions.assertTrue(exists, "Не найдена ошибка ввода почтового адреса!");


    }


    @AfterEach
    public void after() {
        driver.quit();

    }

    private void scrollToElementJs(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }


    private void fillInputFieldPhone(WebElement element, String value) {
        scrollToElementJs(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        element.clear();
        element.sendKeys(value);

        //конвертация вводимого номера в масочный формат, т.к атрибут value содержит уже отформатированное значение
        String number = value.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "+7 ($1) $2-$3");
        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", number));
        Assertions.assertTrue(checkFlag, "Поле было заполнено некорректно"); //проверка
    }

    private void fillInputField(WebElement element, String value) {
        scrollToElementJs(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        element.clear();
        element.sendKeys(value);
        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", value));
        Assertions.assertTrue(checkFlag, "Поле было заполнено некорректно");
    }
}
