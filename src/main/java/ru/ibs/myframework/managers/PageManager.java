package ru.ibs.myframework.managers;

import ru.ibs.myframework.pages.CompaniesPage;
import ru.ibs.myframework.pages.DMSPage;
import ru.ibs.myframework.pages.MainPage;

public class PageManager {

    private static PageManager INSTANCE;

    private MainPage mainPage;
    private CompaniesPage companiesPage;
    private DMSPage dmsPage;

    private PageManager() {

    }


    public static PageManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PageManager();
        }
        return INSTANCE;
    }

    public MainPage getMainPage() {
        if (mainPage == null) {
            mainPage = new MainPage();
        }
        return mainPage;
    }

    public CompaniesPage getCompaniesPage() {
        if (companiesPage == null) {
            companiesPage = new CompaniesPage();
        }
        return companiesPage;
    }

    public DMSPage getDMSPage() {
        if (dmsPage == null) {
            dmsPage = new DMSPage();
        }
        return dmsPage;
    }
}
