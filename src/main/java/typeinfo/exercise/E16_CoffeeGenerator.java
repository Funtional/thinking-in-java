package typeinfo.exercise;

import net.mindview.util.Generator;
import typeinfo.coffee2.Coffee;

import java.util.Iterator;

/**
 * 练习16
 * @author Cheng Cheng
 * @date 2017-11-28 17:12
 */
public class E16_CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {

    private int size = 0;

    public E16_CoffeeGenerator() {
    }

    public E16_CoffeeGenerator(int sz) {
        size = sz;
    }

    @Override
    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }

    @Override
    public Coffee next() {
        return Coffee.createRandom();
    }

    class CoffeeIterator implements Iterator<Coffee> {
        int count = size;

        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public Coffee next() {
            count--;
            return E16_CoffeeGenerator.this.next();
        }
    }

    public static void main(String[] args) {
        for (Coffee coffee : new E16_CoffeeGenerator(10)) {
            System.out.println(coffee);
        }
    }
}