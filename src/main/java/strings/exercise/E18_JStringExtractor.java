package strings.exercise;

import net.mindview.util.TextFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 练习18：（8）编写一个程序，读取一个Java源代码文件（可以通过控制台参数提供文件名），打印出代码中所有的普通字符串
 * @author Cheng Cheng
 * @date 2017-11-22 14:51
 */
public class E18_JStringExtractor {
    static final String STR_EXT_REGEX =
            "\"(?:[^\"\\\\\\n\\r]|(?:\\\\(?:[untbrf\\\\'\"]|[0-9A-Fa-f]{4}|[0-7]{1,2}|[0-3][0-7]{2})))*\"";

    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Usage: java E18_JStringExtractor file");
            System.exit(0);
        }
        String src = TextFile.read(args[0]);
        Pattern p = Pattern.compile(STR_EXT_REGEX);
        Matcher m = p.matcher(src);
        while(m.find())
            System.out.println(m.group().
                    substring(1, m.group().length() - 1));
        // "This is NOT a string but a comment!"
        String dummy = "\u003F\u003f\n\060\607";
    }
}