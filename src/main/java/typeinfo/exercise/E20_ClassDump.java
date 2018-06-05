package typeinfo.exercise;

import java.util.ArrayList;

import static net.mindview.util.Print.print;

/**
 * @author Cheng Cheng
 * @date 2017-11-29 15:54
 */
public class E20_ClassDump {
    public static void display(String msg, Object[] vals) {
        print(msg);
        for (Object val : vals) {
            print("   " + val);
        }
    }

    public static void classInfo(Class<?> c) throws Exception {
        print("c.getName(): " + c.getName());
        print("c.getPackage(): " + c.getPackage());
        print("c.getSuperClass(): " + c.getSuperclass());
        // This produces all the classes declared as members:
        display("c.getDeclaredClasses()", c.getDeclaredClasses());
        display("c.getClasses()", c.getClasses());
        display("c.getInterfaces()", c.getInterfaces());
        // The "Declared" version include all
        // version, not just public:
        display("c.getDeclaredMethods()", c.getDeclaredMethods());
        display("c.getMethods()", c.getMethods());
        display("c.getDeclaredConstructors()", c.getDeclaredConstructors());
        display("c.getConstructors()", c.getConstructors());
        display("c.getDeclaredFields()", c.getDeclaredFields());
        if (c.getSuperclass() != null) {
            print("Base class: ---------");
            classInfo(c.getSuperclass());
        }
        print("End of " + c.getName());
    }

    public static void main(String[] args) throws Exception {
        classInfo(Integer.class);
    }
}