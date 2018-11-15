import ru.kracoz.utils.CompareHelper;
import ru.kracoz.utils.CompareParser;
import ru.kracoz.utils.Stash;
import ru.kracoz.utils.StringHelper;
import ru.kracoz.utils.entities.StringCompareRule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example {
    public static void main(String[] args) {
        Map<String, List<StringCompareRule>> parsedEntry = CompareParser.parseEntry(new HashMap<String, String>() {{
            put("A", "##EQUALS A");
            put("Book", "##DOES_NOT_START_WITH А");
            put("08.11.2018 18:00:00", "##AFTER dd.MM.yyyy HH:mm:ss -- 08.11.2018 17:00:00");
        }});
        System.out.println(parsedEntry.entrySet().stream().allMatch(e -> checkValueRulesMatch(e.getValue(), e.getKey())));
        Stash.put("<stash>", "<example>");

        System.out.println((String) Stash.getValue("<stash>"));
        System.out.println("Сгенерированное значение параметра из проперти: " + StringHelper.getGenerateValue("/#Pexample"));
        System.out.println("Сгенерированное значение параметра из stash: " + StringHelper.getGenerateValue("/#V<stash>"));
        System.out.println("Сгенерированное значение даты: " + StringHelper.getGenerateValue("/#d(dd.MM.yyyy HH:mm:ss)"));
        System.out.println(StringHelper.getGenerateValue("/#%My nickname is #_ #Pnickname #_ #n10#_#S5"));
    }

    public static boolean checkValueRulesMatch(List<StringCompareRule> rules, String cellValue) {
        return rules.stream().allMatch(r -> CompareHelper.apply(r, cellValue));
    }

}
