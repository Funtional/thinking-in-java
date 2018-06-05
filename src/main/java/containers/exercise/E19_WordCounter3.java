package containers.exercise;

import containers.SimpleHashMap;
import net.mindview.util.TextFile;

import java.util.List;

public class E19_WordCounter3 {
    public static void main(String[] args) {
        String directory = "src/main/java/containers/exercise";
        List<String> words = new TextFile(directory + "/E12_MapsDemo.java", "\\W+");
        SimpleHashMap<String, Integer> map = new SimpleHashMap<String, Integer>();
        for (String word : words) {
            Integer freq = map.get(word);
            map.put(word, freq == null ? 1 : freq + 1);
        }
        System.out.println(map);
    }
}
