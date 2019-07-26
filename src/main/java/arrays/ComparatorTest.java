package arrays;

import net.mindview.util.Generated;

import java.util.Arrays;
import java.util.Comparator;

import static net.mindview.util.Print.print;

/**
 * @author Cheng Cheng
 * @date 2017-12-08 16:50
 */
public class ComparatorTest {
    public static void main(String[] args) {
        CompType[] a = Generated.array(CompType.class, CompType.generator(), 12);
        print("before sorting:");
        print(Arrays.toString(a));
        Arrays.sort(a, new CompTypeComparator());
        print("after sorting:");
        print(Arrays.toString(a));
    }
}

class CompTypeComparator implements Comparator<CompType> {
    @Override
    public int compare(CompType o1, CompType o2) {
        return (o1.j < o2.j ? -1 : (o1.j == o2.j ? 0 : 1));
    }
}