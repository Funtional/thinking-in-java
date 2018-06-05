package strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Cheng Cheng
 * @date 2017-11-16 16:38
 */
public class Finding {
    public static void main(String[] args) {
        Matcher m = Pattern.compile("\\w+").matcher("Evening is full of the linnet's wings");
        while (m.find()) {
            System.out.print(m.group() + " ");
        }

        System.out.println();

        int i = 0;
        while (m.find(i++)) {
            System.out.printf("%s ", m.group());
        }
    }
}