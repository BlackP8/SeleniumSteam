package utilities.convert_utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Pavel Romanov 20.12.2022
 */
public class ConvertUtil {
    private static final String REGEX = "\\d+";
    private static final String REGEX2 = "\\D+";
    private static final String REPLACEMENT = "";
    private static final String SPLITTER = "PLAYING";

    public static String findNumber(String text) {
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(text);

        while(m.find()) {
            text = m.group();
            break;
        }

        return text;
    }

    public static String[] findPlayersCount(String text) {
        String[] parts = text.split(SPLITTER);
        parts[0] = parts[0].replaceAll(REGEX2, REPLACEMENT);
        parts[1] = parts[1].replaceAll(REGEX2, REPLACEMENT);

        return parts;
    }
}
