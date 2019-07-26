package typeinfo.exercise;

import generics.coffee.Coffee;
import generics.coffee.CoffeeGenerator;
import net.mindview.util.TypeCounter;

import java.util.Iterator;

import static net.mindview.util.Print.*;

/**
 * 练习12：（3）将第15章中的CoffeeGenerator.java类用于TypeCounter
 *
 * @author Cheng Cheng
 * @date 2017-11-28 15:11
 */
public class E12_CoffeeCount {
    public static void main(String[] args) {
        TypeCounter counter = new TypeCounter(Coffee.class);
        for (Iterator<Coffee> it = new CoffeeGenerator(20).iterator(); it.hasNext(); ) {
            Coffee coffee = it.next();
            counter.count(coffee);
            printnb(coffee.getClass().getSimpleName() + " ");
        }
        print();
        print(counter);
    }
}