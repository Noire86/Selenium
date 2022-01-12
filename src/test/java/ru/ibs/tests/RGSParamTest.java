package ru.ibs.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.ibs.myframework.managers.PageManager;
import ru.ibs.myframework.pages.CompaniesPage;
import ru.ibs.myframework.pages.DMSPage;
import ru.ibs.myframework.pages.MainPage;
import ru.ibs.tests.base.Base;


public class RGSParamTest extends Base {

    @ParameterizedTest
    @DisplayName("RGS parametrized test")
    @CsvFileSource(resources = "fields.csv")
    public void test(String name, String phone, String email, String region) { //укажем какие параметры мы должны передавать при каждой итерации


        PageManager.getInstance().getMainPage()
                .selectNavMenu("Компаниям")
                .checkCompaniesPageTitle("компаний", "Страхование компаний и юридических лиц | Росгосстрах")
                .selectBaseMenuButton("Здоровье")
                .selectHealthMenuButton("Добровольное медицинское страхование")
                .checkDMSTitle("Добровольное", "Добровольное медицинское страхование для компаний и юридических лиц в Росгосстрахе")
                .clickApplicationButton()
                .checkH2Title("Оперативно позвоним", "Отсутствует заголовок формы ввода данных!");


        try {
            Thread.sleep(1600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PageManager.getInstance().getDMSPage()
                .fillInputField("Иванов Иван Иванович", name)
                .fillInputField("+7 XXX XXX XX XX", phone)
                .fillInputField("hello@email.com", email)
                .fillInputField("Введите", region)
                .selectCheckbox()

                .clickFieldViaActions("Введите")
                .selectRegionItem(region)

                .scrollToSubmit()
                .clickSubmitButton()
                .checkError("Введите корректный адрес электронной почты");

    }

}
