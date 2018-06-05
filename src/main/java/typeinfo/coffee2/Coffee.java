//: generics/coffee/Coffee.java
package typeinfo.coffee2;

import typeinfo.factory.Factory;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Coffee {
    private static long counter = 0;

    private final long id = counter++;

    private static Random rand = new Random(47);

    private static List<Factory<? extends Coffee>> coffeeFactories = Arrays.asList(
            new Americano.Factory(), new Breve.Factory(), new Latte.Factory(),
            new Mocha.Factory(), new Cappuccino.Factory());

    public static Coffee createRandom() {
        int n = rand.nextInt(coffeeFactories.size());
        return coffeeFactories.get(n).create();
    }

    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }
}