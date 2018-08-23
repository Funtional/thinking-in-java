package concurrency.exercise;

import concurrency.LiftOff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.*;

import static net.mindview.util.Print.print;

/**
 * @author Cheng Cheng
 * @date 2018-08-03 17:08
 */

class LiftOffRunner implements Runnable {
    private BlockingQueue<LiftOff> rockets;

    public LiftOffRunner(BlockingQueue<LiftOff> queue) {
        rockets = queue;
    }

    public void add(LiftOff lo) throws InterruptedException {
        rockets.put(lo);
    }

    @Override
    public void run() {
        //noinspection Duplicates
        try {
            while (!Thread.interrupted()) {
                LiftOff rocket = rockets.take();
                rocket.run(); // Use this thread
            }
        } catch (InterruptedException e) {
            print("Waking from take()");
        }
        print("Exiting LiftOffRunner");
    }
}

class LiftOffProducer implements Runnable {
    private LiftOffRunner runner;

    public LiftOffProducer(LiftOffRunner runner) {
        this.runner = runner;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                runner.add(new LiftOff(5));
            }
        } catch (InterruptedException e) {
            print("Waking from put()");
        }
        print("Exiting LiftOffProducer");
    }
}

public class E28_TestBlockingQueues2 {
    public static void getkey() {
        try {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getkey(String msg) {
        print(msg);
        getkey();
    }

    public static void test(String msg, BlockingQueue<LiftOff> queue) {
        ExecutorService exec = Executors.newFixedThreadPool(2);
        print(msg);
        LiftOffRunner runner = new LiftOffRunner(queue);
        LiftOffProducer producer = new LiftOffProducer(runner);
        exec.execute(runner);
        exec.execute(producer);
        getkey("Press 'ENTER' (" + msg + ")");
        exec.shutdownNow();
        print("Finished " + msg + " test");
    }

    public static void main(String[] args) {
        // Unlimited size
        test("LinkedBlockingQueue", new LinkedBlockingQueue<LiftOff>());
        // Fixed size
        test("ArrayBlockingQueue", new ArrayBlockingQueue<LiftOff>(3));
        // Size of 1
        test("SynchronousQueue", new SynchronousQueue<LiftOff>());
    }
}
