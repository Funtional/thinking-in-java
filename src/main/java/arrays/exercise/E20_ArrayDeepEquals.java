package arrays.exercise;

import java.util.Arrays;

import static net.mindview.util.Print.print;

/**
 * @author Cheng Cheng
 * @date 2017-12-08 15:50
 */
public class E20_ArrayDeepEquals {
    public static void main(String[] args) {
        int[][] table1 = {
                {1, 2, 3},
                {4, 5},
                {7, 8, 9, 10}
        };
        int[][] table2 = {
                {1, 2, 3},
                {4, 5},
                {7, 8, 9, 10}
        };
        Integer[][] table3 = {
                {1, 2, 3},
                {4, 5},
                {7, 8, 9, 10}
        };
        Integer[][] table4 = {
                {1, 2, 3},
                {6, 5, 4},
                {7, 8}
        };
        print(Arrays.deepEquals(table1, table2));
        print(Arrays.deepEquals(table1, table3));
        print(Arrays.deepEquals(table1, table4));

        // Let us check manually for equality between table1 and
        // table3
        boolean res = true;
        exit_loop:
        for (int i = 0; i < table1.length; i++) {
            for (int j = 0; j < table1[i].length; j++) {
                if (table1[i][j] != table3[i][j]) {
                    res = false;
                    break exit_loop;
                }
            }
        }
        System.out.println(res);
    }
}