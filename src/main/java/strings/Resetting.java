package strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * reset()
 *
 * @author Cheng Cheng
 * @date 2017-11-21 18:10
 */
public class Resetting {
    public static void main(String[] args) {
        Matcher m = Pattern.compile("[frb][aiu][gx]").matcher("fix the rug with bags");
        while (m.find()) {
            System.out.print(m.group() + " ");
        }
        System.out.println();
        m.reset("fix the rug with bags");
        while (m.find()) {
            System.out.print(m.group() + " ");
        }
    }
}