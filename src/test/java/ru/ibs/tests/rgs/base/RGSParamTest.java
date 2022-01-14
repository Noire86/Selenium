package ru.ibs.tests.rgs.base;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.ibs.myframework.managers.PageManager;


public class RGSParamTest extends Base {

    @ParameterizedTest
    @CsvFileSource(resources = "fields.csv")
    @Description("RGSTest Params")
    @DisplayName("Parametrized RGS test")
    public void test(String name, String phone, String email, String region) { //укажем какие параметры мы должны передавать при каждой итерации


        PageManager.getInstance().getMainPage()
                .selectNavMenu("Компаниям")
                .checkCompaniesPageTitle("компаний", "Страхование компаний и юридических лиц | Росгосстрах")
                .selectBaseMenuButton("Здоровье")
                .selectHealthMenuButton("Добровольное медицинское страхование")
                .checkDMSTitle("Добровольное", "Добровольное медицинское страхование для компаний и юридических лиц в Росгосстрахе")
                .clickApplicationButton()
                .checkH2Title("Оперативно позвоним", "Отсутствует заголовок формы ввода данных!");




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
