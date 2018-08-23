package concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * @author Cheng Cheng
 * @date 2018-08-01 16:30
 */

class WaitPerson2 implements Runnable {
    private Restaurant2 restaurant;
    boolean notified;

    public WaitPerson2(Restaurant2 restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null) {
                        wait(); // ... for the chef to produce a meal
                    }
                }
                print("Waitperson got " + restaurant.meal);
                synchronized (restaurant.busBoy) {
                    restaurant.busBoy.notified = true;
                    restaurant.busBoy.meal = restaurant.meal;
                    restaurant.busBoy.notifyAll(); // Clean up
                }

                synchronized (restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll(); // Ready for another
                }

                synchronized (this) {
                    if (!notified) {
                        wait(); // ... for the bus boy to clean up
                    }
                    notified = false;
                }
            }
        } catch (InterruptedException e) {
            print("Chef interrupted");
        }
    }
}

class BusBoy implements Runnable {
    private Restaurant2 restaurant;
    boolean notified;
    Meal meal;

    BusBoy(Restaurant2 restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    if (!notified) {
                        wait(); // ... for meal delivery
                    }
                    notified = false;
                }
                print("Busboy cleaned up " + meal);
                synchronized (restaurant.waitPerson) {
                    restaurant.waitPerson.notified = true;
                    restaurant.waitPerson.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            print("BusBoy interrupted");
        }
    }
}

class Chef2 implements Runnable {
    private int count;
    private Restaurant2 restaurant;

    public Chef2(Restaurant2 restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal != null) {
                        wait(); // ... for the meal to be taken
                    }
                }
                if (++count == 10) {
                    print("Out of food, closing");
                    restaurant.exec.shutdownNow();
                }
                printnb("Order up! ");
                synchronized (restaurant.waitPerson) {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            print("Chef interrupted");
        }
    }
}

class Restaurant2 {
    Meal meal;
    WaitPerson2 waitPerson = new WaitPerson2(this);
    BusBoy busBoy = new BusBoy(this);
    Chef2 chef = new Chef2(this);

    ExecutorService exec = Executors.newCachedThreadPool();

    public Restaurant2() {
        exec.execute(chef);
        exec.execute(waitPerson);
        exec.execute(busBoy);
    }
}

public class E26_Restaurant2 {
    public static void main(String[] args) {
        new Restaurant2();
    }
}
