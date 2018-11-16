package ru.kracoz.utils;


import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

public class JsonHelper {
    private JsonHelper() {
        throw new IllegalAccessError("Utility class");
    }

    public static Map<String, String> parseJsonValue(String jsonValue) {
        Map<String, String> result = new HashMap<>();
        new JsonParser().parse(jsonValue).getAsJsonObject().entrySet().forEach(v -> {
            String value = v.getValue().toString();
            if (value.startsWith("\"") && value.endsWith("\"")) {
                result.put(v.getKey(), value.substring(1, value.length() - 1));
            } else {
                result.put(v.getKey(), value);
            }
        });
        return result;
    }

    public static String getValueFromJson(String jsonValue, String keyName) {
        Map<String, String> json = parseJsonValue(jsonValue);
        if (!json.containsKey(keyName)) {
            throw new AssertionError(String.format("Не удалось найти искомый параметр '%s' в JSON", keyName));
        }
        return json.get(keyName);
    }
}
