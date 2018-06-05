package containers.exercise;

import containers.SlowMap;
import net.mindview.util.TextFile;

import java.util.List;

public class E15_WordCounter2 {
    public static void main(String[] args) {
        final String dir = "src/main/java/containers/exercise";
        List<String> words = new TextFile(dir + "/E12_MapsDemo.java", "\\W+");
        SlowMap<String, Integer> map = new SlowMap<String, Integer>();
        for (String word : words) {
            Integer freq = map.get(word);
            map.put(word, freq == null ? 1 : freq + 1);
        }
        System.out.println(map);
    }
}
