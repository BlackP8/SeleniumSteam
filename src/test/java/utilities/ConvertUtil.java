package utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Pavel Romanov 20.12.2022
 */
public class ConvertUtil {
    private static final String REGEX = "\\d+";

    public static String findNumber(String text) {
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(text);

        while(m.find()) {
            text = m.group();
            break;
        }

        return text;
    }
}
