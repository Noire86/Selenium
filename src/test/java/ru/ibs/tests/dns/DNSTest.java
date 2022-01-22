package ru.ibs.tests.dns;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.ibs.myframework.managers.PageManager;
import ru.ibs.myframework.pageobjects.dns.SearchFieldPage;
import ru.ibs.tests.Base;

public class DNSTest extends Base {

    @Tag("dns")
    @ParameterizedTest
    @CsvFileSource(resources = "fields.csv")
    @DisplayName("DNS Site test")
    @Description("DNS Site goods test and validation")
    public void DNS(String item1, String item2, int warranty, int times){
        PageManager.getInstance().getPage(SearchFieldPage.class)
                .fillSearch(item1)
                .selectSuggestion(item1)
                .checkSearchPageH1()
                .clickProduct(item1)
                .createProductModel()
                .clickWarrantyMenu()
                .clickWarrantyRadioButton(String.valueOf(warranty))
                .updateProductWarranty(item1)
                .updateProductPrice(item1)
                .clickBuy()
                .goToSearchField()
                .fillSearch(item2)
                .selectSuggestion(item2)
                .checkSearchPageH1()
                .clickProduct(item2)
                .createProductModel()
                .clickBuy()
                .checkSummary()
                .clickCart()
                .checkWarranty(item1,warranty)
                .checkPrice(item1)
                .checkPrice(item2)
                .checkSummary()
                .deletePosition(item2)
                .addItem(item1, times)
                .checkSummary()
                .restoreRemovedItem()
                .checkRestoredItem(item2)
                .checkSummary();
    }
}
