package ru.ibs.myframework.managers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class PropertiesManager {


    private final Properties properties = new Properties();

    private PropertiesManager() {
        loadProperties();
        loadCustomProperties();
    }

    private static class Holder {
        public static final PropertiesManager INSTANCE = new PropertiesManager();
    }


    public static PropertiesManager getInstance() {
        return Holder.INSTANCE;
    }


    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getProperty(String key, String def) {
        return properties.getProperty(key, def);
    }


    private void loadProperties() {
        try {

            String path = System.getProperty("propertiesPath", "src/main/resources/properties/");
            String fileName = System.getProperty("propertiesFilename", "framework");


            properties.load(new FileInputStream(path + fileName + ".properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomProperties() {
        properties.forEach((fileKey, fileValue) -> System.getProperties().forEach((customKey, customValue) -> {
            if (fileKey.toString().equals(customKey.toString()) && !fileValue.toString().equals(customValue.toString())) {
                properties.setProperty(fileKey.toString(), customValue.toString());
            }
        }));
    }
}
