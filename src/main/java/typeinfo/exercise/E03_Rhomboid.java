package typeinfo.exercise;

import typeinfo.*;

import java.util.Arrays;
import java.util.List;

/**
 * 练习3：（2）将Rhomboid（菱形）加入Shape.java中。创建一个Rhomboid，将其向上转型成Shape，然后向下转型回Rhomboid。
 * 试着将其向下转型成Circle，看看会发生什么。
 *
 * @author Cheng Cheng
 * @date 2017-11-23 15:59
 */
public class E03_Rhomboid {
    public static void main(String[] args) {
        List<Shape> shapes = Arrays.asList(
                new Circle(), new Square(), new Triangle(), new Rhomboid());
        for (Shape shape : shapes) {
            shape.draw();
        }
        // Upcast to shape:
        Shape shape = new Rhomboid();
        // Downcast to Rhomboid:
        Rhomboid r = (Rhomboid) shape;
        // Downcast to Circle. Succeeds at compile time,
        // but fails at runtime with a ClassCastException:
        //! Circle c = (Circle)shape;
    }
}

