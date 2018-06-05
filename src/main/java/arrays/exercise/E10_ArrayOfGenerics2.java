package arrays.exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cheng Cheng
 * @date 2017-12-07 10:45
 */
public class E10_ArrayOfGenerics2 {
    public static void main(String[] args) {
        ArrayList<List<String>> ls = new ArrayList<>();
        ls.add(new ArrayList<String>());
        // Compile-time checking produces an error:
        //! ls.add(new ArrayList<Integer>());
        ls.get(0).add("Array of Generics");
        System.out.println(ls.toString());
    }
}
