package concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Cheng Cheng
 * @date 2018-07-31 15:16
 */
public class E22_BusyWait {
    private static volatile boolean flag;
    private static int spins;

    public static void main(String[] args) throws InterruptedException {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        return;
                    }
                    flag = true;
                }
            }
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    // The busy-wait loop
                    while (!flag && !Thread.currentThread().isInterrupted()) {
                        spins++;
                    }
                    System.out.println("Spun " + spins + " times");
                    spins = 0;
                    flag = false;
                    if (Thread.interrupted()) {
                        return;
                    }
                }
            }
        };

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(r1);
        exec.execute(r2);
        TimeUnit.SECONDS.sleep(1);
        exec.shutdownNow();
    }

}
