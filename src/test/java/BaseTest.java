import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.myframework.util.DriverUtils;
import ru.ibs.myframework.util.TestUtils;


public class BaseTest {

    static WebDriver driver;
    static WebDriverWait wait;
    static Actions actions;
    static TestUtils utils;

    @BeforeAll
    static void beforeAll() {


    }

    @BeforeEach
    void beforeEach() {
        driver = DriverUtils.getAnyDriver(System.getProperty("browser", "opera"));
        wait = new WebDriverWait(driver, 20, 2500);
        actions = new Actions(driver);
        utils = new TestUtils(driver, wait);

        driver.manage().window().maximize();
        driver.get("https://rgs.ru");
    }

    @AfterEach
    void afterEach() {
        driver.quit();

    }

    @AfterAll
    static void afterAll() {
        //dummy
    }
}
