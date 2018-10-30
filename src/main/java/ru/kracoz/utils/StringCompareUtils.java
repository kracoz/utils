package ru.kracoz.utils;



import java.util.function.BiFunction;

public class StringCompareUtils {
    private StringCompareUtils() {
        throw new IllegalAccessError("Utility class.");
    }

    /**
     * Правила сравнения строк
     */
    public enum Rule {
        /**
         * Первая строка содержит текст второй строки без учета регистра
         */
        CONTAINS_IGNORE_CASE((a, b) -> a.toLowerCase().contains(b.toLowerCase())),
        /**
         * Первая строка соержит текст второй строки с учетом регистра
         */
        CONTAINS(String::contains),
        /**
         * Первая строка не содержит текст второй строки
         */
        DOES_NOT_CONTAIN((a, b) -> !a.contains(b)),
        /**
         *Строки не совпадают.
         */
        DOES_NOT_EQUAL((a, b) -> !a.equals(b)),
        /**
         *
         */
        DOES_NOT_END_WITH((a, b) -> !a.endsWith(b)),
        /**
         *
         */
        DOES_NOT_START_WITH((a, b) -> !a.startsWith(b)),
        /**
         *
         */
        END_WITH_IGNORE_CASE((a, b) -> a.toLowerCase().endsWith(b.toLowerCase())),
        /**
         *
         */
        END_WITH(String::endsWith),
        /**
         *
         */
        EQUALS_IGNORE_CASE(String::equalsIgnoreCase),
        /**
         *
         */
        EQUALS(String::equals),
        /**
         *
         */
        STARTS_WITH_IGNORE_CASE((a, b) -> a.toLowerCase().startsWith(b.toLowerCase())),
        /**
         *
         */
        STARTS_WITH(String::startsWith);
        private BiFunction<String, String, Boolean> action;

        Rule(BiFunction<String, String, Boolean> action) {
            this.action = action;
        }

        /**
         * Применение указанного правила к переданным параметрам
         *
         * @param a Первая строка
         * @param b Вторая строка
         * @return <code>true</code>, если правило выполняется;<code>false</code>, если нет.
         */
        public boolean apply(String a, String b) {
            return this.action.apply(a == null ? "" : a, b == null ? "" : b);
        }
    }

    /**
     * Сравнение двух строк по указанным правилам.
     *
     * @param a Первая строка
     * @param b Вторая строка
     * @param r Список правил для сравнения
     * @return <code>true</code>, если правило выполняется;<code>false</code>, если нет.
     */
    public static boolean compare(String a, String b, Rule... r) {
        boolean res = true;
        for (Rule rule : r) {
            res = res && rule.apply(a, b);
        }
        return res;
    }

    /**
     * Сравнение двух строк по указанным правилам.
     *
     * @param a Первая строка
     * @param b Вторая строка
     * @param r Список правил для сравнения
     * @return <code>true</code>, если правило выполняется;<code>false</code>, если нет.
     */
    public static boolean compare(String a, String b, Rule r) {
        return r.apply(a, b);

    }

}
