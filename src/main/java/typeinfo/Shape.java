package typeinfo;

/**
 * @author Cheng Cheng
 * @date 2017-11-23 16:01
 */
public abstract class Shape {

    public void draw() {
        System.out.println(this + " draw()");
    }

    public abstract String toString();
}

