package typeinfo.exercise;

import typeinfo.Circle;
import typeinfo.Rhomboid;
import typeinfo.Shape;

/**
 * @author Cheng Cheng
 * @date 2017-11-23 16:16
 */
public class E04_Instanceof {
    public static void main(String[] args) {
        // Upcast to shape:
        Shape shape = new Rhomboid();
        // Downcast to Rhomboid:
        Rhomboid r = (Rhomboid) shape;
        // Test before Downcasting:
        Circle c = null;
        if (shape instanceof Circle)
            c = (Circle) shape;
        else
            System.out.println("shape not a Circle");
    }
} /* Output:
shape not a Circle
*///:~