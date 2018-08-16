package concurrency;

/**
 * @author Cheng Cheng
 * @date 2018-08-12 21:30
 */
public class Chopstick {
    private boolean taken;

    public synchronized void take() throws InterruptedException {
        while (taken) {
            wait();
        }
        taken = true;
    }

    public synchronized void drop() {
        taken = false;
        notifyAll();
    }
}
