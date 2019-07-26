package concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.printnb;

/**
 * @author Cheng Cheng
 * @date 2018-07-31 15:16
 */
public class E22_WaitNotify {

    public static void main(String[] args) throws InterruptedException {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                        synchronized (this) {
                            notify();
                        }
                    } catch (Exception e) {
                        return;
                    }
                }
            }
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    synchronized (r1) {
                        try {
                            r1.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                        printnb("Cycled ");
                    }
                }
            }
        };

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(r2);
        exec.execute(r1);
        TimeUnit.SECONDS.sleep(1);
        exec.shutdownNow();
    }

}
