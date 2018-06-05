package typeinfo.exercise;

import static net.mindview.util.Print.*;
import net.mindview.util.TypeCounter;

/**
 * 练习13：（3）将本章中的RegisteredFactories.java示例用于TypeCounter。
 * @author Cheng Cheng
 * @date 2017-11-28 15:28
 */
public class E13_PartCount {
    public static void main(String[] args) {
        TypeCounter counter = new TypeCounter(Part.class);
        Part part;
        for (int i = 0; i < 20; i++) {
            part = Part.createRandom();
            printnb(part.getClass().getSimpleName() + " ");
            counter.count(part);
        }
        print();
        print(counter);
    }
}