package ru.kracoz.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {

    private StringHelper() {
        throw new IllegalAccessError("Utility class");
    }

    public static boolean matchesPattern(String text, String pattern) {
        return Pattern.compile(pattern).matcher(text).matches();
    }

    public static String getGenerateValue(String value) {
        try {
            if (value.startsWith("/")) {

                return generateValue(value.replaceFirst("/", ""));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            return value;
        }


    /**
     *
     * @param variable
     * @return -возвращает сгенерированную строку
     * <p>
     *  # - символ разделения, символ идущий за ним сообщает как сгенерировать имя переменной
     *  % - символы идущие после тега, заносятсяв результирующую сгенерированную строку без изменений
     *  * - после тега указывается имя переменной, которая будет записана в stash
     *  n - после тега указывается длина числа, которое должно быть сгенерировано
     *  s - после тега указывается колличество прописных символов латинского алфавита, которое требуется сгенерить
     *  S - после тега указывается колличество заглавных символов латинского алфавита, которое требуется сгенерить
     *  r - после тега указывается колличество прописных символов кирилицы, которое требуется сгенерить
     *  R - после тега указывается колличество заглавных символов кирилицы, которое требуется сгенерить
     *  h - после тега указывается длинна 16-тиричного числа, которое требуется сгенерить. Буквы в нижнем регистре
     *  H - после тега указывается длинна 16-тиричного числа, которое требуется сгенерить. Буквы в верхнем регистре
     *  d - генерится текущее время и дата, в скобках указывается формат генерируемой даты
     *  _ - вставка пробела, колличество пробелов будет соответствовать колличеству символов _
     *  V - после тега указывается имя переменной в стеше
     *  P - после тега указывается имя параметра из пропрерти
     * </p>
     */
    protected static String generateValue(String variable) {
        if (!variable.contains("#")) {
            return variable;
        }
        boolean saveInStashFlag = false;
        String stashVariable = "";
        String endString = "";
        String[] splitVariable = variable.replaceAll("( )+#", "#").split("#");
        for (String s : splitVariable) {
            if (!s.isEmpty()) {
                switch (s.charAt(0)) {
                    case 'n':
                        endString = endString + StringGenerate.generateNumeric(s.replaceAll("n", ""));
                        break;
                    case 's':
                        endString = endString + StringGenerate.generateENLowerCase(s.replaceAll("s", ""));
                        break;
                    case 'S':
                        endString = endString + StringGenerate.generateENUpperCase(s.replaceAll("S", ""));
                        break;
                    case 'r':
                        endString = endString + StringGenerate.generateRULowerCase(s.replaceAll("r", ""));
                        break;
                    case 'R':
                        endString = endString + StringGenerate.generateRUUpperCase(s.replaceAll("R", ""));
                        break;
                    case 'h':
                        endString = endString + StringGenerate.generateHEXLowerCase(s.replaceAll("h", ""));
                        break;
                    case 'H':
                        endString = endString + StringGenerate.generateHEXUpperCase(s.replaceAll("H", ""));
                        break;
                    case 'd':
                        Pattern p = Pattern.compile("\\((.*?)\\)");
                        Matcher m = p.matcher(s);
                        m.find();
                        s = m.group(1);
                        Calendar c = new GregorianCalendar();
                        endString = endString + new SimpleDateFormat(s).format(c.getTime());
                        break;
                    case '%':
                        endString = endString + s.replaceAll("%", "");
                        break;
                    case '*':
                        saveInStashFlag = true;
                        stashVariable = s.replaceFirst("\\*", "");
                        break;
                    case '_':
                        endString = endString + s.replaceAll("_", " ");
                        break;
                    case 'V':
                        String stashKey = s.replaceFirst("V","");
//                        checkStashKey(stashKey);
                        endString = endString+ Stash.getValue(stashKey);
                        break;
                    case 'P':
                        String propKey = s.replaceFirst("P","");
                        endString = endString + Props.get(propKey,"");
                        break;
                    default:throw new Error("Шаблон для генерации значения тега содержит неподдерживаемый символю");

                }
            }
        }
        if (saveInStashFlag) {
            Stash.put(stashVariable,endString);
        }
        return endString;
    }

}
