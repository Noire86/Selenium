package ru.ibs.myframework.managers;

import ru.ibs.myframework.pages.CompaniesPage;
import ru.ibs.myframework.pages.DMSPage;
import ru.ibs.myframework.pages.MainPage;

public class PageManager {

    private static PageManager INSTANCE;

    private PageManager() {
    }


    public static PageManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PageManager();
        }
        return INSTANCE;
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
