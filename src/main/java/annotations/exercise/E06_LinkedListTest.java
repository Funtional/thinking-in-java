package annotations.exercise;

import net.mindview.atunit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class E06_LinkedListTest {
    LinkedList<String> testObject = new LinkedList<String>();

    @Test
    void initialization() {
        assert testObject.isEmpty();
    }

    @Test
    void _contains() {
        testObject.add("one");
        assert testObject.contains("one");
    }

    @Test
    void _remove() {
        testObject.add("one");
        testObject.remove("one");
        assert testObject.isEmpty();
    }

    public static void main(String[] args) throws Exception {
        File desktop = new File("C:\\Users\\Administrator\\Desktop");
        List<File> sqlFiles = new ArrayList<>();
        for (File file : desktop.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".sql")) {
                sqlFiles.add(file);
            }
        }
        System.out.println("桌面当前含有" + sqlFiles.size() + "个SQL脚本文件");

        String line = null;
        String output = desktop.getAbsolutePath() + "\\cobar\\starts_with_SECURITY_19.sql";
        if (sqlFiles.size() > 0) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(output))) {
                for (File f : sqlFiles) {
                    if (f.length() > 0) {
                        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                            while ((line = br.readLine()) != null) {
                                bw.write(line);
                                bw.newLine();
                            }
                            System.out.println(f.getName() + " 脚本整理完成!文件大小：" + f.length());
                        }
                        bw.newLine();
                        bw.newLine();
                        bw.newLine();
                        bw.newLine();
                    }
                }
            }
        }
        sqlFiles.forEach(File::deleteOnExit);
    }
}
