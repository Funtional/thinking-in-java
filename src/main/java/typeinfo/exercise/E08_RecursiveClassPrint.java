package typeinfo.exercise;

/**
 * @author Cheng Cheng
 * @date 2017-11-23 18:11
 */
public class E08_RecursiveClassPrint {
    static void printClasses(Class<?> c){
        // getSuperclass() returns null on Object:
        if (c == null) return;
        System.out.println(c.getName());
        // Produces the interfaces that this class
        // implements:
        for(Class<?> k:c.getInterfaces()){
            System.out.println(k.getName());
            printClasses(k.getSuperclass());
        }

        printClasses(c.getSuperclass());
    }

    public static void main(String args[]) throws Exception {
        printClasses(FancyToy.class);
    }
}