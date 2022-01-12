package ru.ibs.myframework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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


    public DMSPage clickApplicationButton() {
        pageUtils.click(applicationButton);
        return pageManager.getDMSPage();
    }

    public DMSPage clickFieldViaActions(String placeholder){
        WebElement el = pageUtils.getElementByAttribute("placeholder", placeholder, inputList);
        pageUtils.clickViaActions(el);
        return pageManager.getDMSPage();
    }

    public DMSPage clickSubmitButton() {
        pageUtils.click(submitButton);
        return pageManager.getDMSPage();
    }

    public DMSPage selectCheckbox() {
        pageUtils.clickViaActions(checkbox);
        return pageManager.getDMSPage();
    }

    public DMSPage selectRegionItem(String region) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@placeholder=\"Введите\"]/../../..//span")));
        pageUtils.clickItemFromList(region, regionList);
        return pageManager.getDMSPage();
    }

    public DMSPage fillInputField(String placeholder, String value) {

        if (placeholder.equals("userTel")) {
            pageUtils.fillInputPhone(pageUtils.getElementByAttribute("placeholder", placeholder, inputList), value);
        } else {
            pageUtils.fillInput(pageUtils.getElementByAttribute("placeholder", placeholder, inputList), value);
        }
        return pageManager.getDMSPage();
    }

    public DMSPage checkDMSTitle(String contains, String expectedTitle) {
        pageUtils.assertTitle(contains, expectedTitle);
        return pageManager.getDMSPage();
    }

    public DMSPage checkH2Title(String h2Text, String assertMsg) {
        pageUtils.assertElementVisibility(h2, h2Text, assertMsg);
        return pageManager.getDMSPage();
    }

    public DMSPage checkError(String errorMsg) {
        pageUtils.assertErrorField(emailError, errorMsg);
        return pageManager.getDMSPage();
    }

    public DMSPage scrollToSubmit() {
        pageUtils.scrollJS(submitButton);
        return pageManager.getDMSPage();
    }

}
