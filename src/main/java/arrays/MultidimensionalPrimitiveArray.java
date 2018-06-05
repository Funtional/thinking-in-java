package arrays;

import java.util.Arrays;

/**
 * @author Cheng Cheng
 * @date 2017-12-06 14:45
 */
public class MultidimensionalPrimitiveArray {
    public static void main(String[] args) {
        int[][] a = {
                {1, 2, 3},
                {4, 5, 6}
        };
        System.out.println(Arrays.deepToString(a));
    }
}