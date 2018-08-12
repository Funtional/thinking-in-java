package concurrency.exercise;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Cheng Cheng
 * @date 2018-08-02 15:25
 */

class FlowQueue<T> {
    private Queue<T> queue = new LinkedList<T>();
    private int maxSize;

    public FlowQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized void put(T v) throws InterruptedException {
        while (queue.size() >= maxSize) {
            wait();
        }
        queue.offer(v);
        maxSize++;
        notifyAll();
    }

    public synchronized T get() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T value = queue.poll();
        maxSize--;
        notifyAll();
        return value;
    }
}

class Item {
    private static int counter;
    private final int id = counter++;

    @Override
    public String toString() {
        return "Item " + id;
    }
}

// Produces Item objects
class Producer implements Runnable {
    private int delay;
    private FlowQueue<Item> output;

    public Producer(FlowQueue<Item> output, int sleepTime) {
        this.output = output;
        this.delay = sleepTime;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                output.put(new Item());
                TimeUnit.MILLISECONDS.sleep(delay);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

// Consumes Item objects
class Consumer implements Runnable {
    private int delay;
    private FlowQueue<Item> input;

    public Consumer(FlowQueue<Item> input, int sleepTime) {
        this.input = input;
        this.delay = sleepTime;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                System.out.println(input.get());
                TimeUnit.MILLISECONDS.sleep(delay);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

public class E24_ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        int producerSleep = 200;
        int consumerSleep = 1;
        FlowQueue<Item> fq = new FlowQueue<>(100);
        ExecutorService exec = Executors.newFixedThreadPool(2);
        exec.execute(new Producer(fq, producerSleep));
        exec.execute(new Consumer(fq, consumerSleep));
        TimeUnit.SECONDS.sleep(2);
        exec.shutdownNow();
    }
}
