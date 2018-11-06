package ru.kracoz.utils;


import org.apache.commons.lang.RandomStringUtils;

public class StringGenerate {
    private static char[] chardictionaryRu = new char[]{'й', 'ц', 'у', 'к', 'е', 'н', 'г', 'ш', 'щ', 'з', 'х',
            'ъ', 'ф', 'ы', 'в', 'а', 'п', 'р', 'о', 'л', 'д', 'ж', 'э', 'я', 'ч', 'с', 'м', 'и', 'т', 'ь', 'б', 'ю'};

    private static char[] chardictionaryEn = new char[]{'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a',
            's', 'd', 'f', 'g', 'h', 'j', 'k', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm'};


    private static char[] numericdictionary = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private static char[] hexdictionary = new char[]{'e', 'a', 'd', 'f', '0', '1', '2', '3', '4', '5', '6', 'c', '7', 'b', '8', '9'};

    private StringGenerate() {
    }

    public static String generateRULowerCase(String... vars) {
        return RandomStringUtils.random(Integer.parseInt(vars[0].trim()), chardictionaryRu).toLowerCase();
    }

    public static String generateRUUpperCase(String... vars) {
        return RandomStringUtils.random(Integer.parseInt(vars[0].trim()), chardictionaryRu).toUpperCase();
    }

    public static String generateENLowerCase(String... vars) {
        return RandomStringUtils.random(Integer.parseInt(vars[0].trim()), chardictionaryEn).toLowerCase();
    }

    public static String generateENUpperCase(String... vars) {
        return RandomStringUtils.random(Integer.parseInt(vars[0].trim()), chardictionaryEn).toUpperCase();
    }
    public static String generateHEXLowerCase(String... vars) {
        return RandomStringUtils.random(Integer.parseInt(vars[0].trim()), hexdictionary).toLowerCase();
    }

    public static String generateHEXUpperCase(String... vars) {
        return RandomStringUtils.random(Integer.parseInt(vars[0].trim()), hexdictionary).toUpperCase();
    }

    public static String generateNumeric(String... vars) {
        String generic = RandomStringUtils.random(Integer.parseInt(vars[0].trim()), numericdictionary);
        if (generic.charAt(0) == '0') {
            generic = "1" + generic.substring(1);
        }
        return generic;
    }

}
