import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class RGSTest {
    WebDriver driver;
    WebDriverWait wait;


    @Before
    public void before() {
        System.setProperty("webdriver.opera.driver", "src/test/resources/operadriver.exe");
        driver = new OperaDriver();
        wait = new WebDriverWait(driver,10);

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://rgs.ru");
        driver.manage().window().maximize();
    }


    @Test
    public void test() {
        //выбираем категорию Компаниям
        WebElement companiesButton = driver.findElement(By.xpath("//a[@href='/for-companies']")); //обнаружили элемент
        wait.until(ExpectedConditions.elementToBeClickable(companiesButton)); // ожидаем его доступности
        companiesButton.click(); //кликаем


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("Страхование компаний и юридических лиц | Росгосстрах", driver.getTitle());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        WebElement healthButton = driver.findElement(By.xpath("//span[contains(text(), 'Здоровье') and contains(@class, 'padding')]"));
        wait.until(ExpectedConditions.elementToBeClickable(healthButton));
        healthButton.click();


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }


    @After
    public void after() {
        driver.quit();

    }
}
