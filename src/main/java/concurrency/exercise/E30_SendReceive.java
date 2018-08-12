package concurrency.exercise;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * @author Cheng Cheng
 * @date 2018-08-12 12:08
 */
class CharQueue extends LinkedBlockingQueue {

}

class Sender implements Runnable {
    private Random rand = new Random(47);
    private CharQueue out = new CharQueue();

    public CharQueue getOut() {
        return out;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (int c = 'A'; c <= 'z'; c++) {
                    out.put(c);
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                }
            }
        } catch (InterruptedException e) {
            print(e + " Sender write exception");
        }
    }
}

class Receiver implements Runnable {
    private CharQueue in;

    public Receiver(Sender sender) {
        in = sender.getOut();
    }

    @Override
    public void run() {
        try {
            while (true) {
                printnb("Read: " + in.take() + ", ");
            }
        } catch (InterruptedException e) {
            print(e + " Receiver interrupted");
        }
    }
}


public class E30_SendReceive {
    public static void main(String[] args) throws InterruptedException {
        Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(sender);
        exec.execute(receiver);
        TimeUnit.SECONDS.sleep(4);
        exec.shutdownNow();
    }
}
