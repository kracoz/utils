package ru.kracoz.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by user on 06.11.2018.
 */
public class Props {

    private static Props instanse;
    private static Properties properties;

    private Props() {
        initProperties();
    }

    private static void initProperties() {
        String sConfigFile = System.getProperty("TagConfigFile", "config/application.properties");
        properties = new Properties();
        try (InputStream streamFromResources = Props.class.getClassLoader().getResourceAsStream(sConfigFile)) {
            InputStreamReader isr = new InputStreamReader(streamFromResources, "UTF-8");
            properties.load(isr);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Props getInstanse() {
        if (instanse == null) {
            instanse = new Props();
        }
        return instanse;
    }

    private String getProp(String name) {
        String val = getProps().getProperty(name, "");
        if (val.isEmpty()) {
            System.out.printf("Property %s was not found in properties file", name);
        }
        return val.trim();
    }

    //вернуть проперти как объект
    public static Properties getProps() {
        initProperties();
        return properties;
    }

    //вернуть проперти из файла по имени
    public static String get(String prop) {
        return getInstanse().getProp(prop);
    }

    public static String get(String prop, String defaultValue) {
        String value = getInstanse().getProp(prop);
        if (value.isEmpty()) {
            return defaultValue;
        }
        return value;
    }
}
