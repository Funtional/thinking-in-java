package concurrency.exercise;

import net.mindview.util.CountingGenerator;
import net.mindview.util.Generator;

import java.util.List;
import java.util.concurrent.*;

class ExchangerProducer<T> implements Runnable {
    private Generator<T> generator;
    private Exchanger<List<T>> exchanger;
    private List<T> holder;

    ExchangerProducer(Exchanger<List<T>> exchg, Generator<T> gen, List<T> holder) {
        exchanger = exchg;
        generator = gen;
        this.holder = holder;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                for (int i = 0; i < E34_ExchangerDemo2.size; i++) {
                    holder.add(generator.next());
                }
                // Exchange full for empty:
                exchanger.exchange(holder);
            }
        } catch (InterruptedException e) {
            // OK to terminate this way
        }
    }
}

class ExchangerConsumer<T> implements Runnable {
    private Exchanger<List<T>> exchanger;
    private List<T> holder;
    private volatile T value;

    ExchangerConsumer(Exchanger<List<T>> ex, List<T> holder) {
        this.exchanger = ex;
        this.holder = holder;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                holder = exchanger.exchange(holder);
                for (T x : holder) {
                    value = x; // Fetch out value
                    holder.remove(x); // OK for CopyOnWriteArrayList

                }
            }
        } catch (InterruptedException e) {
            // OK to terminate this way
        }
        System.out.println("Final value: " + value);
    }
}

public class E34_ExchangerDemo2 {
    static int size = 10;
    static int delay = 5;

    public static void main(String[] args) throws InterruptedException {
        if (args.length > 0) {
            size = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            delay = Integer.parseInt(args[1]);
        }

        ExecutorService exec = Executors.newCachedThreadPool();
        Exchanger<List<Integer>> xc = new Exchanger<>();
        List<Integer>
                producerList = new CopyOnWriteArrayList<>(),
                consumerList = new CopyOnWriteArrayList<>();
        exec.execute(new ExchangerProducer<Integer>(xc, new CountingGenerator.Integer(), producerList));
        exec.execute(new ExchangerConsumer<Integer>(xc, consumerList));

        TimeUnit.SECONDS.sleep(delay);
        exec.shutdownNow();
    }
}
