package strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 组
 * @author Cheng Cheng
 * @date 2017-11-16 17:08
 */
public class Groups {

    static public final String POEM =
            "Twas brillig, and the slithy toves\n" +
                    "Did gyre and gimble in the wabe.\n" +
                    "All mimsy were the borogoves,\n" +
                    "And the mome raths outgrabe.\n\n" +
                    "Beware the Jabberwock, my son,\n" +
                    "The jaws that bite, the claws that catch.\n" +
                    "Beware the Jubjub bird, and shun\n" +
                    "The frumious Bandersnatch.";

    public static void main(String[] args) {
        //组由一组括号括起来
        //总共5个组，最外面的是第0组
        //(?m)开启多行支持
        Matcher m = Pattern.compile("(?m)(\\S+)\\s+((\\S+)\\s+(\\S+))$")
                .matcher(POEM);
        while (m.find()) {
            for (int j = 0; j <= m.groupCount(); j++) {
                System.out.print("[" + m.group(j) + "]");
            }
            System.out.println();
        }

        // Output:
        // [the slithy toves][the][slithy toves][slithy][toves]
    }

}