import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.myframework.util.DriverUtils;


public class BaseTest {

    static WebDriver driver;
    static WebDriverWait wait;
    static Actions actions;

    @BeforeAll
    static void beforeAll() {

        driver = DriverUtils.getAnyDriver(System.getProperty("browser", "opera"));
        wait = new WebDriverWait(driver, 20, 2500);
        actions = new Actions(driver);

    }

    @BeforeEach
    void beforeEach() {
        driver.manage().window().maximize();
    }

    @AfterEach
    void afterEach() {

    }

    @AfterAll
    static void afterAll() {

    }
}
