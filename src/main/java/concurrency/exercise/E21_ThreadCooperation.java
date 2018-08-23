package concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * @author Cheng Cheng
 * @date 2018-07-31 14:48
 */
public class E21_ThreadCooperation {
    public static void main(String[] args) {
        Runnable coop1 = new Coop1(),
                 coop2 = new Coop2(coop1);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(coop2);
        exec.execute(coop1);
        Thread.yield();
        exec.shutdown();
    }
}

class Coop1 implements Runnable {
    public Coop1() {
        print("Constructed Coop1");
    }

    @Override
    public void run() {
        print("Coop1 going into wait");
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        print("Coop1 exited wait");
    }
}

class Coop2 implements Runnable {
    Runnable otherTask;

    public Coop2(Runnable otherTask) {
        this.otherTask = otherTask;
        print("Constructed Coop2");
    }

    @Override
    public void run() {
        print("Coop2 pausing 5 seconds");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ignored) {
        }
        print("Coop2 calling notifyAll");
        synchronized (otherTask) {
            otherTask.notifyAll();
        }
    }
}