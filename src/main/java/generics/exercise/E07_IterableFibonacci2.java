package generics.exercise;

import generics.Fibonacci;

import java.util.Iterator;

class IterableFibonacci implements Iterable<Integer> {
    private int count;

    private Fibonacci fib = new Fibonacci();

    public IterableFibonacci(int count) {
        this.count = count;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return count > 0;
            }

            @Override
            public Integer next() {
                count--;
                return fib.next();
            }
        };
    }

}


public class E07_IterableFibonacci2 {
    public static void main(String[] args) {
        for (int i : new IterableFibonacci(18)) {
            System.out.print(i + " ");
        }
    }
}