package ru.kracoz.utils;

import ru.kracoz.utils.entities.StringCompareRule;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс для парсинга правил сравнения.
 */
public class CompareParser {
    private static final String RULES_DELIMITER_REGEX = " (?=##)";
    private static final String PARAMS_DELIMITER_REGEX = " -- ";
    private static final String RULE_PARAMS_DELIMITER_REGEX = "\\s+";
    private static final int NUMBER_OF_PARTS = 2;
    private static final int ZERO_PARAMETERS_RULE = 1;
    private static final List ZERO_PARAMETER = Collections.EMPTY_LIST;

    private CompareParser() {
        throw new IllegalAccessError("Utility class.");
    }

    public static Map<String, List<StringCompareRule>> parseEntry(Map<String, String> entry) {
        Map<String, List<StringCompareRule>> parsedEntry = new HashMap<>();
        entry.entrySet().forEach(s -> parsedEntry.put(s.getKey(), parseRule(s.getValue())));
        return parsedEntry;
    }

    public static List<StringCompareRule> parseRule(String value) {
        return Arrays.stream(value.split(RULES_DELIMITER_REGEX)).map(v -> {
            String[] array = v.split(RULE_PARAMS_DELIMITER_REGEX, NUMBER_OF_PARTS);
            List<String> params = array.length == ZERO_PARAMETERS_RULE ? ZERO_PARAMETER : parseParameters(array[1]);
            CompareHelper.Rule rule = CompareHelper.Rule.valueOf(array[0].substring(2).toUpperCase());
            try {
                checkRuleParamsQtyCompliance(rule, params.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new StringCompareRule(rule, params);
        }).collect(Collectors.toList());
    }

    private static List<String> parseParameters(String params) {
        return Arrays.stream(params.split(PARAMS_DELIMITER_REGEX)).map(StringHelper::getGenerateValue).collect(Collectors.toList());
    }

    private static void checkRuleParamsQtyCompliance(CompareHelper.Rule rule, int paramSize) throws Exception {
        ParamsType type = rule.getParamsType();
        boolean result;
        switch (type) {
            case ZERO:
                result = paramSize == 0;
                break;
            case ONE:
                result = paramSize == 1;
                break;
            case TWO:
                result = paramSize == 2;
                break;
            case ARBITRARY:
                result = paramSize > 0;
                break;
            default:
                throw new Exception(String.format("Переданный тип параметров '%s' не поддерживается.", type.toString()));
        }
    }

}
