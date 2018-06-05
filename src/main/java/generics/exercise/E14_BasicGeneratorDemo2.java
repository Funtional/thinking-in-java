package generics.exercise;

import generics.CountingObject;
import net.mindview.util.BasicGenerator;
import net.mindview.util.Generator;

public class E14_BasicGeneratorDemo2 {
    public static void main(String[] args) {
        Generator<CountingObject> gen = new BasicGenerator<>(CountingObject.class);
        for (int i = 0; i < 5; i++) {
            System.out.println(gen.next());
        }
    }
}
