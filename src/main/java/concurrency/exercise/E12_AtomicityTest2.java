package concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Cheng Cheng
 * @date 2018-04-28 17:07
 */
@SuppressWarnings("ALL")
public class E12_AtomicityTest2 {
    public static void main(String[] args) {
        System.out.println("Press Control-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();

        AtomicityTest2 at = new AtomicityTest2();
        exec.execute(at);

        while (true){
            int value = at.getValue();
            if (value % 2 != 0) {
                System.out.println(value);
                System.exit(0);
            }
        }
    }
}


class AtomicityTest2 implements Runnable {
    private int i;

    public synchronized int getValue() {
        return i;
    }

    public synchronized void evenIncrement() {
        i++;
        i++;
    }

    @Override
    public void run() {
        while (true) {
            evenIncrement();
        }
    }
}