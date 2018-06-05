package containers.exercise;

import java.util.LinkedHashSet;
import java.util.Set;

import static net.mindview.util.Countries.names;

public class E03_VerifySet {
    public static void main(String[] args) {
        Set<String> set = new LinkedHashSet<String>();
        for (int i = 0; i < 5; i++) {
            set.addAll(names(10));
        }
        System.out.println(set);
    }
}
