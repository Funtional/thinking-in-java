package typeinfo.exercise;

import java.util.Arrays;
import java.util.List;

/**
 * 练习5：（3）实现Shape.java中的rotate(Shape)方法，让它能判断它所旋转的是不是Circle（如果是，就不执行）
 *
 * @author Cheng Cheng
 * @date 2017-11-23 16:26
 */
abstract class RShape {
    void draw() {
        System.out.println(this + ".draw()");
    }

    abstract public String toString();

    void rotate(int degrees) {
        System.out.println("Rotating " + this + " by " + degrees + " degrees");
    }
}

class RCircle extends RShape {
    public String toString() {
        return "Circle";
    }

    void rotate(int degrees) {
        throw new UnsupportedOperationException();
    }
}

class RSquare extends RShape {
    public String toString() {
        return "Square";
    }
}

class RTriangle extends RShape {
    public String toString() {
        return "Triangle";
    }
}

public class E05_RotateShapes {
    private static void rotateAll(List<RShape> shapes) {
        for (RShape shape : shapes)
            if (!(shape instanceof RCircle))
                shape.rotate(45);
    }

    public static void main(String[] args) {
        List<RShape> shapes = Arrays.asList(
                new RCircle(), new RSquare(), new RTriangle()
        );
        rotateAll(shapes);
    }
} /* Output:
Rotating Square by 45 degrees
Rotating Triangle by 45 degrees
*///:~