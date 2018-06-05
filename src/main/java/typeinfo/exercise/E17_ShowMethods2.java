//: typeinfo/ShowMethods.java
// Using reflection to show all the methods of a class,
// even if the methods are defined in the base class.
// {Args: ShowMethods}
package typeinfo.exercise;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

import static net.mindview.util.Print.print;

/**
 * 14.6.1 类方法提取器
 * @author Cheng Cheng
 * @date 2017-11-29 14:18
 */
public class E17_ShowMethods2 {
    private static String usage =
            "usage:\n" +
                    "ShowMethods qualified.class.name\n" +
                    "To show all methods in class or:\n" +
                    "ShowMethods qualified.class.name word\n" +
                    "To search for methods involving 'word'";

    private static Pattern pattern = Pattern.compile("\\w+\\.|native\\s|final\\s");

    @SuppressWarnings("all")
    public static void main(String[] args) {
        if (args.length < 1) {
            print(usage);
            System.exit(0);
        }

        int lines = 0;

        try {
            Class<?> clazz = Class.forName(args[0]);
            Method[] methods = clazz.getMethods();
            Constructor<?>[] constructors = clazz.getConstructors();

            if (args.length == 1) {
                for (Method method : methods) {
                    print(pattern.matcher(method.toString()).replaceAll(""));
                }

                for (Constructor<?> constructor : constructors) {
                    print(pattern.matcher(constructor.toString()).replaceAll(""));
                }

                lines = methods.length + constructors.length;
            } else {
                for (Method method : methods) {
                    if (method.toString().indexOf(args[1]) != -1) {
                        print(pattern.matcher(method.toString()).replaceAll(""));
                        lines++;
                    }
                }

                for (Constructor<?> constructor : constructors) {
                    if (constructor.toString().indexOf(args[1]) != -1) {
                        print(pattern.matcher(constructor.toString()).replaceAll(""));
                        lines++;
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            print("No such class: " + e);
        }
    }
}