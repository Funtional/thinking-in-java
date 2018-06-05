package arrays;

import net.mindview.util.Generated;

import java.util.Arrays;
import java.util.Collections;

import static net.mindview.util.Print.print;

/**
 * @author Cheng Cheng
 * @date 2017-12-08 16:43
 */
public class Reverse {
    public static void main(String[] args) {
        CompType[] a = Generated.array(new CompType[12], CompType.generator());
        print("before sorting:");
        print(Arrays.toString(a));
        Arrays.sort(a, Collections.reverseOrder());
        print("after sorting:");
        print(Arrays.toString(a));
    }
}