package containers.exercise;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static net.mindview.util.Countries.*;
import static net.mindview.util.Print.print;

public class E02_ACountries {
    public static void main(String[] args) {
        TreeMap<String, String> map = new TreeMap<String, String>(capitals());
        TreeSet<String> set = new TreeSet<String>(names());
        String b = null;
        for (String s : map.keySet()) {
            if (s.startsWith("B")) {
                b = s;
                break;
            }
        }
        Map<String, String> aMap = map.headMap(b);
        Set<String> aSet = set.headSet(b);
        print("aMap = " + aMap);
        print("aSet = " + aSet);
    }
}
