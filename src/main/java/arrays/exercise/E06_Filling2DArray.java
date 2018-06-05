package arrays.exercise;

import java.util.Arrays;

/**
 * @author Cheng Cheng
 * @date 2017-12-06 18:20
 */
public class E06_Filling2DArray {
    static BerylliumSphere[][] fill(int xLen, int yLen) {
        BerylliumSphere[][] a = new BerylliumSphere[xLen][yLen];
        for (int i = 0; i < xLen; i++) {
            for (int j = 0; j < yLen; j++) {
                a[i][j] = new BerylliumSphere();
            }
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(fill(3, 3)));
    }
}