package ru.ibs.myframework.managers;

import ru.ibs.myframework.pages.RGS.CompaniesPage;
import ru.ibs.myframework.pages.RGS.DMSPage;
import ru.ibs.myframework.pages.RGS.MainPage;

public final class PageManager {

    private PageManager() {
    }

    private static class Holder{
        public static final PageManager INSTANCE = new PageManager();
    }


    public static PageManager getInstance() {
        return Holder.INSTANCE;
    }

    public MainPage getMainPage() {
        return new MainPage();
    }

    public CompaniesPage getCompaniesPage() {
        return new CompaniesPage();
    }

    public DMSPage getDMSPage() {
        return new DMSPage();
    }
}
