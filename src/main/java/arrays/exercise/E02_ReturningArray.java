package arrays.exercise;

import java.util.Arrays;

/**
 * @author Cheng Cheng
 * @date 2017-12-06 14:42
 */
public class E02_ReturningArray {
    private static BerylliumSphere[] createArray(int size) {
        BerylliumSphere[] a = new BerylliumSphere[size];
        for (int i = 0; i < size; i++) {
            a[i] = new BerylliumSphere();
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(createArray(10)));
    }
}