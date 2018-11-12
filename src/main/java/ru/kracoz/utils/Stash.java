package ru.kracoz.utils;


import java.util.HashMap;
import java.util.Map;

public class Stash {
    private static final ThreadLocal<Map<String, Object>> THREAD_VAULT = new ThreadLocal<>();

    private Stash() {
        throw new IllegalAccessError("Utility class");
    }

    private static Map<String, Object> getThreadVault() {
        Object vault = (Map) THREAD_VAULT.get();
        if (vault == null) {
            vault = new HashMap();
            THREAD_VAULT.set((Map) vault);
        }
        return (Map) vault;
    }

    public static Map<String, Object> asMap() {
        return getThreadVault();
    }

    public static void put(String key, Object value) {
        getThreadVault().put(key, value);
    }

    public static <T> T getValue(String key) {
        return (T) getThreadVault().get(key);
    }

    public static <T> T remove(String key) {
        return (T) getThreadVault().remove(key);
    }

    public static void clear() {
        getThreadVault().clear();
    }
}
