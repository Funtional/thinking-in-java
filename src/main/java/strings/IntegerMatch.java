package strings;

import java.util.Arrays;

/**
 * 13.6.1 正则表达式-基础
 * @author Cheng Cheng
 * @date 2017-11-15 16:25
 */
public class IntegerMatch {

    public static void main(String[] args) {
        System.out.println("-1234".matches("-?\\d+"));
        System.out.println("5678".matches("-?\\d+"));
        System.out.println("+911".matches("-?\\d+"));
        System.out.println("+911".matches("(-?|\\+)\\d+"));

        String str="A B C D E F G H I J K L M O P Q";
        System.out.println(Arrays.toString(str.split(" ", 5)));
    }
}