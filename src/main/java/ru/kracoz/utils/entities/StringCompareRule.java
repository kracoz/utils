package ru.kracoz.utils.entities;

import ru.kracoz.utils.CompareHelper;

import java.util.List;

public class StringCompareRule {
    private final CompareHelper.Rule rule;
    private final List<String> params;

    public StringCompareRule(CompareHelper.Rule rule, List<String> params) {
        this.rule = rule;
        this.params = params;
    }
    public CompareHelper.Rule getRule(){
        return rule;
    }

    public List<String> getParams() {
        return params;
    }
}
