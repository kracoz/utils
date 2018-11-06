package ru.kracoz.utils;


public class CompareHelper {
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
