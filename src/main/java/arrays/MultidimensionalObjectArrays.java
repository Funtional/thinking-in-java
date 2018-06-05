package arrays;

import java.util.Arrays;

/**
 * @author Cheng Cheng
 * @date 2017-12-06 15:40
 */
public class MultidimensionalObjectArrays {
    public static void main(String[] args) {
        BerylliumSphere[][] spheres = {
                {
                        new BerylliumSphere(), new BerylliumSphere()
                },
                {
                        new BerylliumSphere(), new BerylliumSphere(),
                        new BerylliumSphere(), new BerylliumSphere()
                },
                {
                        new BerylliumSphere(), new BerylliumSphere(),
                        new BerylliumSphere(), new BerylliumSphere(),
                        new BerylliumSphere(), new BerylliumSphere(),
                        new BerylliumSphere(), new BerylliumSphere()
                }
        };
        System.out.println(Arrays.deepToString(spheres));
    }
}