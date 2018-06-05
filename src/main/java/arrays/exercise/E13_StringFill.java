package arrays.exercise;

import net.mindview.util.CountingGenerator;

/**
 * @author Cheng Cheng
 * @date 2017-12-07 18:40
 */
public class E13_StringFill {
    public static void main(String[] args) {
        String s = new CountingGenerator.String(15).next();
        System.out.println(s);
    }
}
