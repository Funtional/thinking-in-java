package strings.exercise;

import net.mindview.util.TextFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 练习17：（8）编写一个程序，读取一个Java源代码文件（可以通过控制台参数提供文件名），打印出所有注释
 *
 * @author Cheng Cheng
 * @date 2017-11-22 14:25
 */
public class E17_JCommentExtractor {
    static final String CMNT_EXT_REGEX =
            "(?x)(?m)(?s) # Comments, Multiline & Dotall: ON\n" +
                    "/\\* # Match START OF THE COMMENT\n" +
                    "(.*?) # Match all chars\n" +
                    "\\*/ # Match END OF THE COMMENT\n" +
                    "| //(.*?)$ # OR Match C++ style comments\n";

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java E17_JCommentExtractor file");
            System.exit(0);
        }
        String str = TextFile.read(args[0]);
        Pattern p = Pattern.compile(CMNT_EXT_REGEX);
        Matcher m = p.matcher(str);
        while (m.find()) {
            System.out.println(m.group(1) != null ? m.group(1) : m.group(2));
        }
    }
}