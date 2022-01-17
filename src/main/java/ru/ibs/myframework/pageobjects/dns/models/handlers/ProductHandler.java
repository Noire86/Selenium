package ru.ibs.myframework.pageobjects.dns.models.handlers;

import org.junit.jupiter.api.Assertions;
import ru.ibs.myframework.pageobjects.dns.models.Product;
import ru.ibs.myframework.util.PageUtils;

import java.util.ArrayList;
import java.util.List;

public final class ProductHandler implements ModelHandler{

    private List<Product> productList;
    private final PageUtils pageUtils;

    private static class Holder {
        public static final ProductHandler INSTANCE = new ProductHandler();
    }


    private ProductHandler() {
        this.productList = new ArrayList<>();
        this.pageUtils = new PageUtils();
    }


    public static ProductHandler getInstance() {
        return Holder.INSTANCE;
    }


    public Product getProductByName(String nameContains) {
        for(Product pr : productList) {
            if(pr != null && pr.getName().toLowerCase().contains(nameContains)){
                return pr;
            }
        }
        return new Product("dummy", "dummy", 0, 0, 0,null);
    }

    @Override
    public void put(Object p) {
        int beforeSize = productList.size();
        productList.add((Product) p);
        Assertions.assertTrue(productList.size() > beforeSize, "Ошибка добавления элемента " + ((Product) p).getName() + "!");
    }

    @Override
    public void remove(Object p) {
        int beforeSize = productList.size();
        productList.remove((Product) p);
        Assertions.assertTrue(productList.size() < beforeSize, "Ошибка удаления элемента " + ((Product) p).getName() + "!");
    }

    @Override
    public void truncateList() {
        productList.clear();
    }

    public Product updateProductPrice(Product product, int newPrice) {
        product.setPrice(newPrice);
        return product;
    }

    public int getSummaryPrice() {
        int productsSummary = 0;
        for (Product pr : productList) {
            productsSummary += pr.getPrice();
        }
        return productsSummary;
    }

}
