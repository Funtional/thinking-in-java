package containers.exercise;

import net.mindview.util.Countries;

public class E21_LeftToReader {
    public static void main(String[] args) {
        SimpleHashMap2<String, String> m = new SimpleHashMap2<String, String>();
        m.putAll(Countries.capitals(25));
        m.putAll(Countries.capitals(10));
        System.out.println(m);
    }
}
