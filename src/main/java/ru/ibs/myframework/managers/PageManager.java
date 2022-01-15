package ru.ibs.myframework.managers;

import ru.ibs.myframework.pageobjects.BasePage;

import java.util.HashMap;
import java.util.Map;

public final class PageManager {

    private final Map<String, BasePage> pageMap = new HashMap<>();


    private PageManager() {
    }

    private static class Holder {
        public static final PageManager INSTANCE = new PageManager();
    }


    public static PageManager getInstance() {
        return Holder.INSTANCE;
    }



    public <T extends BasePage> T getPage(Class<T> pageClass) {

        if(pageMap.isEmpty() || pageMap.get(pageClass.getName()) == null) {
            try {
                pageMap.put(pageClass.getName(), pageClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return (T) pageMap.get(pageClass.getName());

    }

    public void clearPages(){
        pageMap.clear();
    }


}
