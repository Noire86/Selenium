import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class RGSParamTest extends BaseTest {

    @ParameterizedTest
    @DisplayName("RGS parametrized test")
    @CsvSource({
            "Иванов Василий Петрович, 56856846, qwertyytrewq, Орск",
            "Сергеев Сергей Сергеевич, 9678653434, qwertymail, Уфа",
            "Дмитриев Дмитрий Дмитриевич, 9455679898, qwertybadmail, Пенза"
    })
    public void test(String name, String phone, String email, String region) {
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
        utils.fillInputField(driver.findElement(By.xpath("//input[contains(@name, \"userName\")]")), name);
        utils.fillInputFieldPhone(driver.findElement(By.xpath("//input[contains(@name, \"userTel\")]")), phone);
        utils.fillInputField(driver.findElement(By.xpath("//input[contains(@name, \"userEmail\")]")), email);
        utils.fillInputField(driver.findElement(By.xpath("//input[@placeholder=\"Введите\"]")), region);


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

}
