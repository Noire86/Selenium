import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

public class SiteTest {


    @Test
    public void test() {
        System.setProperty("webdriver.opera.driver", "src/test/resources/operadriver.exe");

        WebDriver driver = new OperaDriver();



        driver.get("https://dreamfinity.org");
    }
}
