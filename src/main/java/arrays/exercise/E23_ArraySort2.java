package arrays.exercise;

import net.mindview.util.Generated;
import net.mindview.util.Generator;
import net.mindview.util.RandomGenerator;

import java.util.Arrays;
import java.util.Collections;

import static net.mindview.util.Print.print;

public class E23_ArraySort2 {
    public static void main(String[] args) {
        Generator<Integer> gen = new RandomGenerator.Integer(1000);
        Integer[] a = Generated.array(new Integer[25], gen);
        print("Unsorted array: " + Arrays.toString(a));
        Arrays.sort(a, Collections.reverseOrder());
        print("Sorted array: " + Arrays.toString(a));
    }
}
