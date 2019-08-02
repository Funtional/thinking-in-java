package concurrency;

import net.mindview.util.DaemonThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * 后台线程的实现与DaemonFromFactory无关，主要是Executors.newCachedThreadPool(new DaemonThreadFactory());传入了参数。
 * 因为被设置为后台线程，所以main()结束时，后台线程也会结束。
 * 如果没有被设置为后台线程，因为DaemonFromFactory.run()是没有退出的死循环，那么程序main线程也将不会结束。
 */
public class DaemonFromFactory implements Runnable {

    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                print(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            print("Interrupted");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /**DaemonThreadFactory对象作为参数传入，这样执行器创建的线程被设置为后台线程了**/
        ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
        for (int i = 0; i < 10; i++) {
            exec.execute(new DaemonFromFactory());
        }
        print("All daemons started");
        TimeUnit.MILLISECONDS.sleep(500); // Run for a while
    }
}
