package ru.ibs.tests.rgs;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.ibs.myframework.managers.PageManager;
import ru.ibs.myframework.pageobjects.rgs.MainPage;
import ru.ibs.tests.Base;

public class RGSTest extends Base {

    @Test
    @Description("RGSTest")
    @DisplayName("Single test")
    public void test() {

        PageManager.getInstance().getPage(MainPage.class)
                .selectNavMenu("Компаниям")
                .checkCompaniesPageTitle("компаний", "Страхование компаний и юридических лиц | Росгосстрах")
                .selectBaseMenuButton("Здоровье")
                .selectHealthMenuButton("Добровольное медицинское страхование")
                .checkDMSTitle("Добровольное", "Добровольное медицинское страхование для компаний и юридических лиц в Росгосстрахе")
                .clickApplicationButton()
                .checkH2Title("Оперативно позвоним", "Отсутствует заголовок формы ввода данных!")
                .fillInputField("Иванов Иван Иванович", "Иванов Иван Иванович")
                .fillInputField("+7 XXX XXX XX XX", "9764554546")
                .fillInputField("hello@email.com", "qwertyqwerty")
                .fillInputField("Введите", "Омск")
                .selectCheckbox()
                .clickFieldViaActions("Введите")
                .selectRegionItem("г Омск")
                .scrollToSubmit()
                .clickSubmitButton()
                .checkError("Введите корректный адрес электронной почты");

    }

}
