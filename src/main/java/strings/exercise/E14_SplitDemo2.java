package strings.exercise;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author Cheng Cheng
 * @date 2017-11-17 15:12
 */
public class E14_SplitDemo2 {
    public static void main(String[] args) {
        String input = "This!!unusual use!!of exclamation!!points";
        System.out.println(Arrays.toString(Pattern.compile("!!").split(input)));
        System.out.println(Arrays.toString(Pattern.compile("!!").split(input, 2)));
    }
}