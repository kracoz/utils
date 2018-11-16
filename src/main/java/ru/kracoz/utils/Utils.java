package ru.kracoz.utils;


import java.util.concurrent.TimeUnit;

public class Utils {
    private Utils() {
        throw new IllegalAccessError("Utility class");
    }

    public static <T, V> T castToRequiredObjectType(V obj, Class<T> tClass) {
        if (!tClass.isAssignableFrom(obj.getClass())) {
            throw new AssertionError("Класс объекта " + obj.getClass().getSimpleName() + " не совпадает с ожидаемым " + tClass.getSimpleName());
        }
        return (T) obj;
    }

    public static void sleep(long timeoutInSeconds) {
        try {
            TimeUnit.SECONDS.sleep(timeoutInSeconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
