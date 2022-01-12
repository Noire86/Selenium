package ru.ibs.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.ibs.myframework.pages.CompaniesPage;
import ru.ibs.myframework.pages.DMSPage;
import ru.ibs.myframework.pages.MainPage;
import ru.ibs.tests.base.Base;

public class RGSTest extends Base {

    @Test
    @DisplayName("RGS insurance query-form test")
    public void test() {

        mainPage.selectNavMenu("Компаниям"); //клик по компаниям
        companiesPage.checkCompaniesPageTitle("компаний", "Страхование компаний и юридических лиц | Росгосстрах");
        companiesPage.selectBaseMenuButton("Здоровье");
        companiesPage.selectHealthMenuButton("Добровольное медицинское страхование");
        dmsPage.checkDMSTitle("Добровольное", "Добровольное медицинское страхование для компаний и юридических лиц в Росгосстрахе");
        dmsPage.clickApplicationButton();
        dmsPage.checkH2Title("Оперативно позвоним", "Отсутствует заголовок формы ввода данных!");


        try {
            Thread.sleep(1600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dmsPage.fillInputField("Иванов Иван Иванович", "Иванов Иван Иванович");
        dmsPage.fillInputField("+7 XXX XXX XX XX", "9764554546");
        dmsPage.fillInputField("hello@email.com", "qwertyqwerty");
        dmsPage.fillInputField("Введите", "Омск");
        dmsPage.selectCheckbox();

        dmsPage.clickFieldViaActions("Введите");

        dmsPage.selectRegionItem("г Омск");


        dmsPage.scrollToSubmit();
        dmsPage.clickSubmitButton();
        dmsPage.checkError("Введите корректный адрес электронной почты");

    }

}
