package concurrency;

import static net.mindview.util.Print.print;

/**
 * @author Cheng Cheng
 * @date 2018-04-18 16:31
 */
public class Joining {
    /**
     * OutPut:
     * Grumpy was interrupted. isInterrupted(): false
     * Doc join completed
     * Sleepy has awakened
     * Dopey join completed
     */
    public static void main(String[] args) {
        Sleeper
                sleeper = new Sleeper("Sleepy", 1500),
                grumpy = new Sleeper("Grumpy", 15000);
        Joiner
                dopey = new Joiner("Dopey", sleeper),
                doc = new Joiner("Doc", grumpy);
        grumpy.interrupt();
    }
}


class Sleeper extends Thread {
    private int duration;

    public Sleeper(String name, int sleepTime) {
        super(name);
        this.duration = sleepTime;
        start();
    }

    @Override
    public void run() {
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            /**
             * main()方法中，调用grumpy.interrupt()，将给grumpy设定一个标志，表明该线程已被中断
             * 然而异常被捕获时，将清理这个标志，所以在异常被捕获的时候，这个标志总为false
             */
            print(getName() + " was interrupted. isInterrupted(): " + isInterrupted());
            return;
        }
        print(getName() + " has awakened");
    }
}

class Joiner extends Thread {
    private Sleeper sleeper;

    public Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    @Override
    public void run() {
        try {
            /**
             * 自身的run()方法，调用第二个线程对象sleeper的join方法，自身线程将会挂起，
             * 直到第二个线程结束（即sleeper.isAlive()返回为false），join()方法才会返回，自身的run()方法的剩余部分才会继续执行。
             */
            sleeper.join();
        } catch (InterruptedException e) {
            print("Interrupted");
        }
        print(getName() + " join completed");
    }
}