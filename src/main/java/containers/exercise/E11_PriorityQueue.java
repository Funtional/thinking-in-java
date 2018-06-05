package containers.exercise;

import java.util.PriorityQueue;
import java.util.Random;

class Item implements Comparable<Item> {
    private static final Random rand = new Random(47);
    private int priority = rand.nextInt(101);

    @Override
    public int compareTo(Item o) {
        return priority < o.priority ? -1 : (priority == o.priority) ? 0 : 1;
    }

    @Override
    public String toString() {
        return Integer.toString(priority);
    }
}

public class E11_PriorityQueue {
    public static void main(String[] args) {
        PriorityQueue<Item> queue = new PriorityQueue<Item>();
        for (int i = 0; i < 10; i++) {
            queue.add(new Item());
        }
        Item item;
        while ((item = queue.poll()) != null) {
            System.out.println(item);
        }
    }
}
