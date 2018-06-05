package concurrency.exercise;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author Cheng Cheng
 * @date 2018-05-11 18:23
 */
public class E14_ManyTimers {
    public static void main(String[] args) throws InterruptedException {
        int numOfTimers = 100;
        for (int i = 0; i < numOfTimers; i++) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println(System.currentTimeMillis());
                }
            }, numOfTimers - i);
        }

        // Wait for timers to expire
        TimeUnit.MILLISECONDS.sleep(2 * numOfTimers);
        System.exit(0);
    }
}
