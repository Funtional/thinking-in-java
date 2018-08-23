package concurrency.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * @author Cheng Cheng
 * @date 2018-08-16 17:46
 */
class Entrance3 implements Runnable {
    private final CountDownLatch latch;
    private static Count count = new Count();
    private static List<Entrance3> entrances = new ArrayList<>();

    private int number;
    private final int id;
    private static volatile boolean canceled;

    Entrance3(CountDownLatch latch, int id) {
        this.latch = latch;
        this.id = id;
        entrances.add(this);
    }

    public static void cancel() {
        canceled = true;
    }

    @Override
    public void run() {
        while (!canceled) {
            synchronized (this) {
                ++number;
            }
            print(this + " Total: " + count.increment());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                print("sleep interrupted");
            }
        }
        latch.countDown();
        print("Stopping " + this);
    }

    public synchronized int getValue() {
        return number;
    }

    public static int getTotalCount() {
        return count.value();
    }

    public static int sumEntrances() {
        int sum = 0;
        for (Entrance3 entrance : entrances)
            sum += entrance.getValue();
        return sum;
    }

    @Override
    public String toString() {
        return "Entrance " + id + ": " + getValue();
    }
}

public class E32_OrnamentalGarden3 {
    public static void main(String[] args) throws InterruptedException {
        // All must share a single CountDownLatch object:
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Entrance3(latch, i));
        }

        TimeUnit.SECONDS.sleep(3);
        Entrance3.cancel();
        exec.shutdown();

        latch.await(); // Wait for results
        print("Total: " + Entrance3.getTotalCount());
        print("Sum of Entrances: " + Entrance3.sumEntrances());
    }
}
