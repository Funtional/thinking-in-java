package strings.exercise;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Cheng Cheng
 * @date 2017-11-16 15:30
 */
public class E11_CheckForMatch2 {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("(?i)((^[aeiou])|(\\s+[aeiou]))\\w+?[aeiou]\\b");
        Matcher m = p.matcher("Arline ate eight apples and one orange while Anita hadn't any");
        while (m.find()) {
            System.out.println("Match \"" + m.group() + "\" at positions " + m.start() + "-" + (m.end() - 1));
        }
    }
}