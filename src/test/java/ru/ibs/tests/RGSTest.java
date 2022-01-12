package ru.ibs.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.ibs.myframework.managers.PageManager;
import ru.ibs.myframework.pages.CompaniesPage;
import ru.ibs.myframework.pages.DMSPage;
import ru.ibs.myframework.pages.MainPage;
import ru.ibs.tests.base.Base;

public class RGSTest extends Base {

    private final PageManager pageManager = PageManager.getInstance();

    @Test
    @DisplayName("RGS insurance query-form test")
    public void test() {

        pageManager.getMainPage().selectNavMenu("Компаниям"); //клик по компаниям

        pageManager.getCompaniesPage().checkCompaniesPageTitle("компаний", "Страхование компаний и юридических лиц | Росгосстрах")
                .selectBaseMenuButton("Здоровье")
                .selectHealthMenuButton("Добровольное медицинское страхование");

        pageManager.getDMSPage().checkDMSTitle("Добровольное", "Добровольное медицинское страхование для компаний и юридических лиц в Росгосстрахе")
                .clickApplicationButton()
                .checkH2Title("Оперативно позвоним", "Отсутствует заголовок формы ввода данных!");


        try {
            Thread.sleep(1600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pageManager.getDMSPage()
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
