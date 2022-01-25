package ru.ibs.myframework.pageobjects.sberbank;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import ru.ibs.myframework.pageobjects.BasePage;

import java.util.List;

public class CreditPage extends BasePage {

    @FindBy(xpath = "//div[@data-test-id=\"realty-cost-input\"]")
    WebElement realtyCost;

    @FindBy(xpath = "//iframe[contains(@src, \"calculator\")]")
    WebElement iFrameCalc;

    @FindBy(xpath = "//h2[@class=\"t-header-big\"]")
    List<WebElement> h2List;


    public CreditPage scrollToH2() {
        WebElement calcH2 = pageUtils.getElementByAttributeContains("textContent", "Рассчитайте ипотеку", h2List, true);
        pageUtils.scrollJS(calcH2);

        return pageManager.getPage(CreditPage.class);
    }


    public CreditPage fillCalcField(String type, String value) {

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iFrameCalc));
        WebElement input = driver.findElement(By.xpath("//label[text()=\"" + type + "\"]/..//input"));
        pageUtils.fillInput(input, value);

        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.switchTo().parentFrame();

        return pageManager.getPage(CreditPage.class);
    }

    public CreditPage deselectCheckBox(String type) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iFrameCalc));
        pageUtils.scrollJS(driver.findElement(By.xpath("//div[text()=\"Услуги, снижающие ставку по кредиту\"]")));
        WebElement checkbox = driver.findElement(By.xpath("//span[text()=\"" + type + "\"]/../..//input"));

        if (checkbox.isSelected()) {
            checkbox.click();
        }

        Assertions.assertFalse(checkbox.isSelected());
        driver.switchTo().parentFrame();


        return pageManager.getPage(CreditPage.class);
    }

    public CreditPage checkResult(String resultType, String value) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iFrameCalc));
        WebElement result = driver.findElement(By.xpath("//span[text()=\"" + resultType + "\"]/..//span[not(@class)]"));


        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(pageUtils.attributeAsInt(result, "innerText"), pageUtils.stringAsInt(value));
        driver.switchTo().parentFrame();
        return pageManager.getPage(CreditPage.class);
    }



}
