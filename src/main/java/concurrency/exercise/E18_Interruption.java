package concurrency.exercise;

import java.util.concurrent.TimeUnit;

/**
 * @author Cheng Cheng
 * @date 2018-07-29 12:29
 */
public class E18_Interruption {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Task());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        t.interrupt();
    }
}

class NonTask {
    static void longMethod() throws InterruptedException {
        TimeUnit.SECONDS.sleep(60);
    }
}

class Task implements Runnable {
    @Override
    public void run() {
        try {
            NonTask.longMethod();
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        } finally {
            // Any cleanup code here...
        }
    }
}
