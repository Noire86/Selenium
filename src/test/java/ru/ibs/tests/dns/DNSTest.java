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
                .createProductModel()
                .clickWarrantyMenu()
                .clickWarrantyRadioButton("24")
                .updateProductWarranty("nintendo switch")
                .updateProductPrice("nintendo switch")
                .clickBuy()
                .goToSearchField()
                .fillSearch("detroit")
                .selectSuggestion("detroit")
                .checkSearchPageH1()
                .clickProduct("detroit")
                .createProductModel()
                .clickBuy()
                .checkSummary()
                .clickCart()
                .checkWarranty("nintendo switch",24)
                .checkPrice("nintendo switch")
                .checkPrice("detroit")
                .checkSummary()
                .deletePosition("detroit")
                .addItem("nintendo switch", 4)
                .checkSummary()
                .restoreRemovedItem();


        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
