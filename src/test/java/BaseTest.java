import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseTest {

    static WebDriver driver;
    static WebDriverWait wait;
    static Actions actions;

    @BeforeAll
    static void beforeAll() {

        driver = getDriver(System.getProperty("browser", "opera"));
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


    private static WebDriver getDriver(String browser) {

        System.setProperty("webdriver." + browser + ".driver", "src/test/resources/" + browser + "driver.exe");

        if (browser.contains("chrome")) {
            return new ChromeDriver();
        } else if (browser.contains("firefox")) {
            return new FirefoxDriver();
        }

        return new OperaDriver();

    }
}
