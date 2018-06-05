package typeinfo.exercise;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Cheng Cheng
 * @date 2017-11-23 16:57
 */
public class E06_Highlight {
    public static void main(String[] args) {
        List<HShape> shapes = Arrays.asList(
                new HCircle(), new HSquare(),
                new HTriangle(), new HSquare(),
                new HTriangle(), new HCircle(),
                new HCircle(), new HSquare());

        HShape.highlight1(HCircle.class);
        HShape.highlight2(HCircle.class);
        for (HShape shape : shapes) {
            shape.draw();
        }

        System.out.println("********");

        // Highlight them all:
        HShape.highlight1(HShape.class);
        HShape.highlight2(HShape.class);
        for (HShape shape : shapes) {
            shape.draw();
        }

        System.out.println("********");
        // Not in the hierarchy:
        HShape.clearHighlight1(ArrayList.class);
        HShape.clearHighlight2(ArrayList.class);
        for (HShape shape : shapes) {
            shape.draw();
        }

        System.out.println("********");
        HShape.clearHighlight2(HShape.class);
        for (HShape shape : shapes) {
            shape.draw();
        }
    }
}


class HShape {
    boolean highlighted = false;

    static List<HShape> shapes = new ArrayList<HShape>();

    HShape() {
        shapes.add(this);
    }

    void draw(){
        System.out.println(this + " draw()");
    }

    public void highlight() {
        this.highlighted = true;
    }

    public void clearHighlight() {
        this.highlighted = false;
    }

    // Basic approach (code duplication)
    static void highlight1(Class<?> type){
        for(HShape shape:shapes){
            if(type.isInstance(shape)){
                shape.highlight();
            }
        }
    }

    static void clearHighlight1(Class<?> type){
        for(HShape shape:shapes){
            if(type.isInstance(shape)){
                shape.clearHighlight();
            }
        }
    }

    // Create an applicator and reuse it. All exceptions
    // indicate programmer error, and are thus
    // RuntimeExceptions:
    static void forEach(Class<?> type, String method) {
        try {
            Method m = HShape.class.getMethod(method, (Class<?>[]) null);
            for (HShape shape : shapes) {
                if (type.isInstance(shape)) {
                    m.invoke(shape, (Object[]) null);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static void highlight2(Class<?> type) {
        forEach(type, "highlight");
    }

    static void clearHighlight2(Class<?> type) {
        forEach(type, "clearHighlight");
    }

    @Override
    public String toString() {
        return getClass().getName() + (highlighted ? " highlighted" : " normal");
    }
}

class HCircle extends HShape {
}

class HSquare extends HShape {
}

class HTriangle extends HShape {
}