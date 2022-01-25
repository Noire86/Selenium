package ru.ibs.tests.sberbank;

import org.junit.jupiter.api.Test;
import ru.ibs.myframework.managers.PageManager;
import ru.ibs.myframework.pageobjects.sberbank.MainPage;
import ru.ibs.tests.Base;

public class SberTest extends Base {


    @Test
    public void test() {
        PageManager.getInstance().getPage(MainPage.class)
                .closeCookies()
                .selectTopMenu("Ипотека")
                .selectSubMenu("Ипотека на готовое жильё")
                .goToCreditPage()
                .scrollToH2()
                .fillCalcField("Стоимость недвижимости", "5 180 000")
                .fillCalcField("Первоначальный взнос", "3 058 000")
                .fillCalcField("Срок кредита", "30")
                .deselectCheckBox("Страхование жизни и здоровья")
                .deselectCheckBox("Электронная регистрация сделки")
                .scrollToH2()
                .checkResult("Сумма кредита", "2 122 000")
                .checkResult("Ежемесячный платеж", "19 570")
                .checkResult("Необходимый доход", "25 192")
                .checkResult("Процентная ставка", "11%");


        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }





}
