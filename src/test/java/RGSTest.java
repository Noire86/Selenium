import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.myframework.util.TestUtils;

import java.util.List;

public class RGSTest extends BaseTest {
    TestUtils utils;

    @BeforeEach
    public void before() {
        driver.get("https://rgs.ru");
        utils = new TestUtils(driver, wait);
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
        utils.fillInputField(driver.findElement(By.xpath("//input[contains(@name, \"userName\")]")), "Иванов Иван Иванович");
        utils.fillInputFieldPhone(driver.findElement(By.xpath("//input[contains(@name, \"userTel\")]")), "9764554546");
        utils.fillInputField(driver.findElement(By.xpath("//input[contains(@name, \"userEmail\")]")), "qwertyqwerty");
        utils.fillInputField(driver.findElement(By.xpath("//input[@placeholder=\"Введите\"]")), "Омск");


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
        utils.scrollToElementJs(submitButton);
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

}
