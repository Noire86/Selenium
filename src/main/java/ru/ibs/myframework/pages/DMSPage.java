package ru.ibs.myframework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class DMSPage extends BasePage {

    @FindBy(xpath = "//button[contains(@class, \"action-item btn--basic\")]//span[contains(text(), \"Отправить заявку\")]")
    private WebElement applicationButton;

    @FindBy(xpath = "//button[@type=\"submit\"][text()=\"Свяжитесь со мной\"]")
    private WebElement submitButton;

    @FindBy(xpath = "//div[contains(@class, \"checkbox-body\")]/input")
    private WebElement checkbox;

    @FindBy(xpath = "//input[contains(@name, \"userEmail\")]/../../span[contains(@class, 'input__error')]")
    private WebElement emailError;

    @FindBy(xpath = "//h2[contains(@class, \"word-breaking title--h2\")]")
    private List<WebElement> h2;

    @FindBy(xpath = "//form[@class=\"form form\"]//input[@placeholder]")
    private List<WebElement> inputList;

    @FindBy(xpath = "//input[@placeholder=\"Введите\"]/../../..//span")
    private List<WebElement> regionList;



    public DMSPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }


    public void checkDMSTitle(String contains, String expectedTitle) {
        pageUtils.assertTitle(contains, expectedTitle);
    }

    public void checkH2Title(String h2Text, String assertMsg) {
        pageUtils.assertElementVisibility(h2, h2Text, assertMsg);
    }

    public void checkError(String errorMsg) {
        pageUtils.assertErrorField(emailError, errorMsg);
    }

    public void clickApplicationButton() {
        pageUtils.click(applicationButton);
    }


    public void clickSubmitButton() {
        pageUtils.click(submitButton);
    }


    public void selectCheckbox() {
        pageUtils.clickViaActions(checkbox);
    }

    public void selectRegionItem(String region) {
        System.out.println(regionList);
        pageUtils.selectItemFromList(region, regionList);
    }

    public void scrollToSubmit() {
        pageUtils.scrollJS(submitButton);
    }

    public void fillInput(String placeholder, String value) {

        String property = "placeholder";

        for (WebElement e : inputList) {
            if (e.getAttribute(property).equals(placeholder)) {
                pageUtils.fillInput(e, value);
            } else if (e.getAttribute(property).equals("userTel")) {
                pageUtils.fillInputPhone(e, value);
            }
        }
    }
}
