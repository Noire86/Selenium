package ru.ibs.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.ibs.myframework.pages.CompaniesPage;
import ru.ibs.myframework.pages.DMSPage;
import ru.ibs.myframework.pages.MainPage;
import ru.ibs.tests.base.Base;


public class RGSParamTest extends Base {

    @ParameterizedTest
    @DisplayName("RGS parametrized test")
    @CsvFileSource(resources = "fields.csv")
    public void test(String name, String phone, String email, String region) { //укажем какие параметры мы должны передавать при каждой итерации
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

        dmsPage.selectCheckbox();
        dmsPage.fillInput("Иванов Иван Иванович", "Иванов Иван Иванович");
        dmsPage.fillInput("+7 XXX XXX XX XX", "9764554546");
        dmsPage.fillInput("hello@email.com", "qwertyqwerty");
        dmsPage.fillInput("Введите", "Омск");

        dmsPage.selectRegionItem("Омск");

        dmsPage.scrollToSubmit();
        dmsPage.clickSubmitButton();
        dmsPage.checkError("Введите корректный адрес электронной почты");
    }

}
