package strings.exercise;

/**
 * @author Cheng Cheng
 * @date 2017-11-15 17:23
 */
public class E09_Replacing2 {
    public static void main(String[] args) {
        String str = "Obama's message was not only deceptive and off point, it was deeply insensitive to his audience.";
        System.out.println(str.replaceAll("(?i)[aeiou]", "_"));
    }
}