package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimplePriorities implements Runnable {
    private int countDown = 5;
    private volatile double d; // No optimization; volatile可以确保不被优化，run方法中的计算可以花费很长时间，让效果更明显。
    private int priority;

    public SimplePriorities(int priority) {
        this.priority = priority;
    }

    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }

    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            // An expensive, interruptable operation: 计算花费很长时间，可以切换到更高优先级的线程执行。
            for (int i = 1; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000 == 0)
                    Thread.yield();
            }
            System.out.println(this);
            if (--countDown == 0) return;
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(
                    new SimplePriorities(Thread.MIN_PRIORITY));
        exec.execute(
                new SimplePriorities(Thread.MAX_PRIORITY));
        exec.shutdown();
    }
}
