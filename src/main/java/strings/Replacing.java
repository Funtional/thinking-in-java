package strings;

/**
 * 13.6.1 正则表达式-替换
 *
 * @author Cheng Cheng
 * @date 2017-11-15 16:46
 */
public class Replacing {

    static String s = "If someone recommends a person or thing to you, they suggest that you would find that person or thing good or useful.";

    public static void main(String[] args) {
        System.out.println(s.replaceFirst("f\\w+", "located"));
        System.out.println(s.replaceAll("someone|suggest|useful", "banana"));
    }
}