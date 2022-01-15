package ru.ibs.tests.dns;

import org.junit.jupiter.api.Test;
import ru.ibs.myframework.managers.PageManager;
import ru.ibs.myframework.pageobjects.dns.SearchFieldPage;
import ru.ibs.tests.Base;

public class DNSTest extends Base {


    @Test
    public void test(){
        PageManager.getInstance().getPage(SearchFieldPage.class)
                .fillSearch("nintendo switch")
                .selectSuggestion("nintendo switch")
                .checkSearchPageH1()
                .clickProduct("nintendo switch")
                .createProductModel(true)
                .clickWarrantyMenu()
                .clickWarrantyRadioButton("24 мес.")
                .updateProductPrice("nintendo switch")
                .clickBuy()
                .goToSearchField()
                .fillSearch("detroit")
                .selectSuggestion("detroit")
                .checkSearchPageH1()
                .clickProduct("detroit")
                .createProductModel(false)
                .clickBuy()
                .checkSummary()
                .clickCart();





        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
