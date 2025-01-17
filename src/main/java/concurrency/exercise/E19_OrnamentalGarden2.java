package concurrency.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * 修改OrnamentalGarden.java，使其使用interrupt()
 *
 * @author Cheng Cheng
 * @date 2018-07-29 12:37
 */
public class E19_OrnamentalGarden2 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Entrance2(i));
        }

        TimeUnit.SECONDS.sleep(3);
        exec.shutdownNow();
        if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS)) {
            print("Some tasks were not terminated!");
        }

        print("Total: " + Entrance2.getTotalCount());
        print("Sum of Entrances: " + Entrance2.sumEntrances());
    }
}

class Entrance2 implements Runnable {
    private int number;
    private final int id;

    private static Count count = new Count();

    private static List<Entrance2> entrances = new ArrayList<>();

    Entrance2(int id) {
        this.id = id;
        entrances.add(this);
    }

    @Override
    public void run() {
        for (; ; ) {
            synchronized (this) {
                number++;
            }
            print(this + " Total: " + count.increment());

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                print("Stopping " + this);
                return;
            }
        }
    }

    public synchronized int getValue() {
        return number;
    }

    @Override
    public String toString() {
        return "Entrance " + id + ": " + getValue();
    }

    public static int getTotalCount() {
        return count.value();
    }

    public static int sumEntrances() {
        int sum = 0;
        for (Entrance2 entrance : entrances) {
            sum += entrance.getValue();
        }
        return sum;
    }
}