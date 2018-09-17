package concurrency.exercise;

import java.util.concurrent.*;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

class ActiveCar {
    private ExecutorService exec = Executors.newSingleThreadExecutor();

    private enum Action {WAX, BUFF}

    private Action lastAction = Action.BUFF;
    private boolean waxOn;
    private final WaitingTask waitingTask = new WaitingTask();
    private final BuffingTask buffingTask = new BuffingTask();

    public void wax() {
        try {
            exec.execute(waitingTask);
        } catch (RejectedExecutionException e) {
        }
    }

    public void buff() {
        try {
            exec.execute(buffingTask);
        } catch (RejectedExecutionException e) {
        }
    }

    public void shutdown() {
        exec.shutdown();
    }

    private static void pause(int delay) {
        try {
            TimeUnit.MILLISECONDS.sleep(delay);
        } catch (InterruptedException e) {
            print("sleep() interrupted");
        }
    }

    private class WaitingTask implements Runnable {
        @Override
        public void run() {
            if (lastAction != Action.WAX) {
                printnb("Wax On! ");
                pause(200);
                waxOn = true;
                lastAction = Action.WAX;
            }
        }
    }

    private class BuffingTask implements Runnable {
        @Override
        public void run() {
            if (lastAction != Action.BUFF) {
                printnb("Wax Off! ");
                pause(200);
                waxOn = false;
                lastAction = Action.BUFF;
            }
        }
    }
}

class WaxCar implements Runnable {
    private final ActiveCar car;

    public WaxCar(ActiveCar car) {
        this.car = car;
    }

    @Override
    public void run() {
        car.wax();
    }
}

class BuffCar implements Runnable {
    private final ActiveCar car;

    public BuffCar(ActiveCar car) {
        this.car = car;
    }

    @Override
    public void run() {
        car.buff();
    }
}

public class E42_ActiveObjectWaxOMatic {
    public static void main(String[] args) throws InterruptedException {
        ActiveCar car = new ActiveCar();
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(2);
        exec.scheduleAtFixedRate(new WaxCar(car), 0, 200, TimeUnit.MILLISECONDS);
        exec.scheduleAtFixedRate(new BuffCar(car), 0, 200, TimeUnit.MILLISECONDS);
        TimeUnit.SECONDS.sleep(5);  // Run for a while...
        exec.shutdownNow();
        car.shutdown();
    }
}
