package arrays;

import java.util.Arrays;

/**
 * @author Cheng Cheng
 * @date 2017-12-06 15:45
 */
public class AssemblingMultidimensionalArrays {
    public static void main(String[] args) {
        Integer[][] a;
        a = new Integer[3][];

        for (int i = 0; i < a.length; i++) {
            a[i] = new Integer[3];
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = i * j;    // Autoboxing
            }
        }

        System.out.println(Arrays.deepToString(a));
    }
}
