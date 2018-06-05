package concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Cheng Cheng
 * @date 2018-04-20 17:37
 */
public class E11_RaceConditionB {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("Press Control-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        SafeTank tank = new SafeTank();
        for (int i = 0; i < 10; i++) {
            exec.execute(new ConsistencyChecker(tank));
        }
        Thread.yield();
        exec.shutdown();
    }
}

class SafeTank extends Tank {
    @Override
    public synchronized void validate() {
        super.validate();
    }

    @Override
    public synchronized void fill() {
        super.fill();
    }

    @Override
    public synchronized void drain() {
        super.drain();
    }
}