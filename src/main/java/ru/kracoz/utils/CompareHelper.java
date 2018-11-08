package ru.kracoz.utils;


import ru.kracoz.utils.entities.StringCompareRule;

import java.util.List;

public class CompareHelper {
    private static final String EMPTY_VALUE = "";

    private CompareHelper() {
        throw new IllegalAccessError("Utility class.");
    }

    public static boolean apply(StringCompareRule rule, String cell) {
        List<String> params = rule.getParams();
        switch (rule.getRule()) {
            //Usage: ##<rule> <param> [--<param>...].
            case CONTAINS_IGNORE_CASE:
                return params.stream().allMatch(p -> StringCompareUtils.compare(cell, p, StringCompareUtils.Rule.CONTAINS_IGNORE_CASE));
            case CONTAINS_IGNORE_CASE_ANY:
                return params.stream().anyMatch(p -> StringCompareUtils.compare(cell, p, StringCompareUtils.Rule.CONTAINS_IGNORE_CASE));
            case CONTAINS:
                return params.stream().allMatch(p -> StringCompareUtils.compare(cell, p, StringCompareUtils.Rule.CONTAINS));
            case CONTAINS_ANY:
                return params.stream().anyMatch(p -> StringCompareUtils.compare(cell, p, StringCompareUtils.Rule.CONTAINS));
            case DOES_NOT_CONTAIN:
                return params.stream().allMatch(p -> StringCompareUtils.compare(cell, p, StringCompareUtils.Rule.DOES_NOT_CONTAIN));
            case DOES_NOT_CONTAIN_ANY:
                return params.stream().anyMatch(p -> StringCompareUtils.compare(cell, p, StringCompareUtils.Rule.DOES_NOT_CONTAIN));
            case DOES_NOT_END_WITH:
                return params.stream().allMatch(p -> StringCompareUtils.compare(cell, p, StringCompareUtils.Rule.DOES_NOT_END_WITH));
            case DOES_NOT_EQUAL:
                return params.stream().allMatch(p -> StringCompareUtils.compare(cell, p, StringCompareUtils.Rule.DOES_NOT_EQUAL));
            case DOES_NOT_START_WITH:
                return params.stream().allMatch(p -> StringCompareUtils.compare(cell, p, StringCompareUtils.Rule.DOES_NOT_START_WITH));
            case END_WITH_IGNORE_CASE:
            case ENDS_WITH_IGNORE_CASE_ANY:
                return params.stream().anyMatch(p -> StringCompareUtils.compare(cell, p, StringCompareUtils.Rule.END_WITH_IGNORE_CASE));
            case END_WITH:
            case ENDS_WITH_ANY:
                return params.stream().anyMatch(p -> StringCompareUtils.compare(cell, p, StringCompareUtils.Rule.END_WITH));
            case EQUALS_IGNORE_CASE:
            case EQUALS_IGNORE_CASE_ANY:
                return params.stream().anyMatch(p -> StringCompareUtils.compare(cell, p, StringCompareUtils.Rule.EQUALS_IGNORE_CASE));
            case EQUALS:
            case EQUALS_ANY:
                return params.stream().anyMatch(p -> StringCompareUtils.compare(cell, p, StringCompareUtils.Rule.EQUALS));
            case STARTS_WITH_IGNORE_CASE:
            case START_WITH_IGNOR_CASE_ANY:
                return params.stream().anyMatch(p -> StringCompareUtils.compare(cell, p, StringCompareUtils.Rule.STARTS_WITH_IGNORE_CASE));
            case STARTS_WITH:
            case STARTS_WITH_ANY:
                return params.stream().anyMatch(p -> StringCompareUtils.compare(cell, p, StringCompareUtils.Rule.STARTS_WITH));
            case EMPTY:
                return StringCompareUtils.compare(cell, EMPTY_VALUE, StringCompareUtils.Rule.EQUALS);
            case NOT_EMPTY:
                return StringCompareUtils.compare(cell, EMPTY_VALUE, StringCompareUtils.Rule.DOES_NOT_EQUAL);
            default:
                throw new Error(String.format("Переданное для сравнения правило '%s' не поддерживается.", rule.getRule()));
        }

    }

    public enum Rule {
        CONTAINS_IGNORE_CASE, CONTAINS, DOES_NOT_CONTAIN, DOES_NOT_EQUAL, DOES_NOT_END_WITH, DOES_NOT_START_WITH,
        BEFORE(ParamsType.TWO),AFTER(ParamsType.TWO),EQUALS_ANY,CONTAINS_IGNORE_CASE_ANY,CONTAINS_ANY,DOES_NOT_CONTAIN_ANY,
        ENDS_WITH_IGNORE_CASE_ANY,ENDS_WITH_ANY,EQUALS_IGNORE_CASE_ANY,START_WITH_IGNOR_CASE_ANY,STARTS_WITH_ANY,
        END_WITH_IGNORE_CASE, END_WITH, EQUALS_IGNORE_CASE, EQUALS, STARTS_WITH_IGNORE_CASE, STARTS_WITH, EMPTY(ParamsType.ZERO),
        NOT_EMPTY(ParamsType.ZERO);

        private ParamsType paramsNum;

        Rule(ParamsType type) {
            this.paramsNum = type;
        }

        Rule() {
            this.paramsNum = ParamsType.ARBITRARY;
        }

        public ParamsType getParamsType() {
            return paramsNum;
        }
    }

}
