package arrays.exercise;

import java.util.Arrays;

/**
 * @author Cheng Cheng
 * @date 2017-12-06 18:23
 */
public class E07_Filling3DArray {

    static BerylliumSphere[][][] fill(int xLen, int yLen, int zLen) {
        BerylliumSphere[][][] a = new BerylliumSphere[xLen][yLen][zLen];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                for (int k = 0; k < a[i][j].length; k++) {
                    a[i][j][k] = new BerylliumSphere();
                }
            }
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(fill(3, 3, 3)));
    }
}
