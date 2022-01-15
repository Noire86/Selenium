package ru.ibs.myframework.managers;

public final class PropertiesManager {


    private PropertiesManager(){
    }

    private static class Holder{
        public static final PropertiesManager INSTANCE = new PropertiesManager();
    }


    public static PropertiesManager getInstance(){
        return Holder.INSTANCE;
    }
}
